package com.dsj.bookstore.book.web.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

import com.dsj.bookstore.book.domain.Book;
import com.dsj.bookstore.book.service.BookService;
import com.dsj.bookstore.category.domain.Category;
import com.dsj.bookstore.category.service.CategoryService;

public class AdminAddBookServlet extends HttpServlet {
	private BookService bookService = new BookService();
	private CategoryService categoryService=new CategoryService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.使用上传三步，把表单数据封装到Book对象中 创建工厂 得到解析器 解析工厂
		 */
		// 创建工厂,限制缓存大小以及创建临时目录
		DiskFileItemFactory factory = new DiskFileItemFactory(200 * 1024,
				new File("F:/f/temp"));
		// 得到解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 单个文件大小为15kb
		sfu.setFileSizeMax(200 * 1024);
		// 使用解析器去解析request对象，得到List<FileItem>
		try {
			List<FileItem> fileItemList = sfu.parseRequest(request);
			/*
			 * 把FileItemList中的数据封装到Book对象中 把所有的普通表单字段封装到map中 再把map中数据封装到Book对象中
			 */
			Map<String, String> map = new HashMap<String, String>();
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					map.put(fileItem.getFieldName(),
							fileItem.getString("UTF-8"));
				}
			}
			Category category = CommonUtils.toBean(map, Category.class);
			Book book = CommonUtils.toBean(map, Book.class);
			book.setCategory(category);
			book.setDel(false);
			book.setBid(CommonUtils.uuid());
			/*
			 * 2.保存上传的文件 保存目录路径 保存文件名称
			 */
			// 得到保存目录
			String savepath = this.getServletContext().getRealPath("/book_img");
			// 得到文件名称:给原来文件前面加uuid前缀以避免名称冲突
			String filename = CommonUtils.uuid() + "_"
					+ fileItemList.get(1).getName();
			//校验文件扩展名
			if(!filename.toLowerCase().endsWith("jpg")){
				request.setAttribute("msg", "您上传的图片不是jpg格式");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp")
						.forward(request, response);
				return;
			}
			File destFile = new File(savepath, filename);
			// 保存上传文件到目标文件位置
			fileItemList.get(1).write(destFile);
			/*
			 * 3.设置book对象的image，即把图片的路径设置给book的image
			 */
			book.setImage("book_img/" + filename);

			/*
			 * 4.使用bookService完成保存
			 */
			bookService.add(book);
			//校验图片的尺寸
			Image image=new ImageIcon(destFile.getAbsolutePath()).getImage();
			if(image.getHeight(null)>200||image.getWidth(null)>200){
				destFile.delete();
				request.setAttribute("msg", "您上传的图片尺寸超过了200*200！");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp")
						.forward(request, response);
				return;
			}
			// 返回
			request.getRequestDispatcher(
					"/admin/AdminBookServlet?method=findAll").forward(request,
					response);
		} catch (Exception e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了15kb");
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp")
						.forward(request, response);
			}
		}

	}

}

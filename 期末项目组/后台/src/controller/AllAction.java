package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AllAction{

	static List<String> books = new ArrayList<String>();
	static List<String> dds = new ArrayList<String>();
	
	@RequestMapping(value = "login")
	public String login(String name, String password,Model model) throws Exception {
		if (!"hhh".equals(name)) {
			model.addAttribute("status", "error");
			model.addAttribute("error", "请输入姓名:hhh");
		}
		return "index.jsp";
	}

	@RequestMapping(value = "registered")
	public String registered(String name, String password,Model model) throws Exception {
		// 填写注册
		return "index.jsp";
	}

	@RequestMapping(value = "addBook")
	public String addBook(String bookName,Model model) throws Exception {
		books.add(bookName);
		return "index.jsp";
	}

	@RequestMapping(value = "getBooks")
	public String getBooks(Model model) throws Exception {
		model.addAttribute("books", books);
		return "index.jsp";
	}

	@RequestMapping(value = "adddd")
	public String adddd(String ddName,Model model) throws Exception {
		dds.add(ddName);
		return "index.jsp";
	}

	@RequestMapping(value = "getdds")
	public String getdds(Model model) throws Exception {
		model.addAttribute("dds", dds);
		return "index.jsp";
	}

	@RequestMapping(value = "getBooksByFf")
	public String getBooksByFf(Model model) throws Exception {
		model.addAttribute("books", books);
		return "index.jsp";
	}
}

package travel.ustc.action;

import travel.ustc.bean.Consumer;

public class LoginAction {
	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Consumer consumer=new Consumer();

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	public String handleLogin(){
		System.out.println("Start to handleLogin()...");
		this.consumer=new Consumer(username,password);
		if(consumer.getCustName().equals("Xiaoming")&&consumer.getPassword().equals("123")){
			return "success";
		}else{
			return "fail";
		}
	}

}

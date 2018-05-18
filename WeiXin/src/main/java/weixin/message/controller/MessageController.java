package weixin.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import util.CommonUtil;
import util.Configure;
import weixin.message.model.Template;
import weixin.message.model.TemplateParam;

@Controller
public class MessageController {
	
	/**
	 * 获取openId
	 * @param request
	 * @param model
	 * @throws IOException 
	 */
	@RequestMapping("/getOpenId")  
    public void getOpenId(String code, HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{ 
		    String requestUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+Configure.getAppID()+"&secret="+Configure.getSecret()+"&js_code="+code+"&grant_type=authorization_code";  
	        JSONObject jsonResult=CommonUtil.httpsRequest(requestUrl, "POST", null);  
	        String openId ="";
	        if(jsonResult!=null){  
	           openId=jsonResult.getString("openid");  
	        }   
	        response.getWriter().append(openId);
    }  
    
	/**
	 * 发送微信消息
	 * @param request
	 * @param model
	 */
	 @RequestMapping("/sendMessage")  
	    public void sendMessage(String openId,String formId,HttpServletRequest request,Model model){  
		    Template tem=new Template();  
			tem.setTemplateId("WsHtokVPQi807XPVUm7HRr7MofSzhroc47hbOc5Yk1s");  
			tem.setFormId(formId);
			tem.setTopColor("#00DD00");  
			tem.setToUser(openId);  
			tem.setUrl("");  
			
			List<TemplateParam> paras=new ArrayList<TemplateParam>();  
			paras.add(new TemplateParam("keyword1","猪猪侠","#0044BB"));  
			paras.add(new TemplateParam("keyword2","999","#0044BB"));  
			paras.add(new TemplateParam("keyword3","成个猪栏最叻就系你","#AAAAAA"));  
			paras.add(new TemplateParam("keyword4","666","#0044BB"));  
			paras.add(new TemplateParam("keyword5","131123456789","#AAAAAA"));  
			          
			tem.setTemplateParamList(paras);        
			boolean result=sendTemplateMsg(tem); 
			System.out.println(result);
	    }  
	 
	 
	 public static boolean sendTemplateMsg(Template template){  
		   String token = getToken(template);
	       boolean flag=false;   
	        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";  
	        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);  
	        String jsonString = template.toJSON();
	        JSONObject jsonResult=CommonUtil.httpsRequest(requestUrl, "POST", jsonString);  
	        if(jsonResult!=null){  
	            int errorCode=jsonResult.getInt("errcode");  
	            String errorMessage=jsonResult.getString("errmsg");  
	            if(errorCode==0){  
	                flag=true;  
	            }else{  
	                System.out.println("模板消息发送失败:"+errorCode+","+errorMessage);  
	                flag=false;  
	            }  
	        }
	        return flag;          
	    }  
		
		
		/**
		 *获取token
		 * @param template
		 * @return
		 */
	    public static String getToken(Template template){  
	        String requestUrl="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx3a5373020d49f671&secret=bb6e6f75e021e88be7ebfda9ffcc14b2";  
	        JSONObject jsonResult=CommonUtil.httpsRequest(requestUrl, "POST", template.toJSON());  
	        if(jsonResult!=null){  
	            String access_token=jsonResult.getString("access_token");  
	            return access_token;
	        }else{  
	        	  return "";     
	        }    
	    }  
}

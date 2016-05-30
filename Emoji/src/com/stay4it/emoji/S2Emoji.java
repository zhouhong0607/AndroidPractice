package com.stay4it.emoji;

import java.util.HashMap;
import java.util.Map;

public class S2Emoji
{
	private static S2Emoji convert;
	private HashMap<String,String> map;
	private S2Emoji()
	{
		// TODO Auto-generated constructor stub
		map=new HashMap<String, String>();
		//添加 对应关系
		map.put("我", "[e]" +  "1f199" + "[/e]");
		
		
		
	}
	public static S2Emoji getInstance()
	{
		if(convert==null)
		{
			convert=new S2Emoji();
		}
		return convert;
	}
	public  String conver2Emoji(String message)
	{
	
		for(String word:map.keySet())
		{
		message=message.replace(word, map.get(word));
		}
		return message;
	}
	
}

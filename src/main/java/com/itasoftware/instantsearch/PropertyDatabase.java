package com.itasoftware.instantsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PropertyDatabase {
	
	TST<String> prop_trie = new TST<String>();
	HashMap<String,HashSet<NrhpProperty>> prop_map = new HashMap<String,HashSet<NrhpProperty>>();
	
	public PropertyDatabase(){
		
	}
	
	public void add(NrhpProperty p)
	{
		add(p.getAddress(), p);
		add(p.getCity(), p);
		add(p.getState(), p);
		
		for (String name : p.getNames()) {
			add(name, p);
		}
	}
	
	private void add(String text, NrhpProperty p){
		if (text == null) return;
		String s = text.toLowerCase();
		
		prop_trie.put(s, s);
		
		//add substrings
		int len = s.length();
		for (int i=1; i<len; i++) {
			prop_trie.put(s.substring(i, len), s);
		}
		
		HashSet<NrhpProperty> map = prop_map.get(s);
		if (map == null)
		{
			map = new HashSet<NrhpProperty>();
			map.add(p);
			prop_map.put(s, map);
		}
		else {
			map.add(p);
		}
	}
	
	public List<NrhpProperty> search(String text){
		String s = text.toLowerCase();
		HashSet<NrhpProperty> set = new HashSet<NrhpProperty>();
		
		for(String key : prop_trie.keysWithPrefix(s)){
		   for(NrhpProperty p: prop_map.get(prop_trie.get(key))){
				set.add(p);	
				if (set.size() > 100) break;
			}
			if (set.size() > 100) break;
		}
	
		return new ArrayList<NrhpProperty>(set);
	}
}

package com.itasoftware.instantsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PropertyDatabase {
	
	//TST<HashSet<Integer>> prop_trie = new TST<HashSet<Integer>>();
	TST<NrhpProperty> prop_trie = new TST<NrhpProperty>();
	HashMap<Integer,NrhpProperty> prop_map = new HashMap<Integer,NrhpProperty>();
	
	public PropertyDatabase(){
		
	}
	
	public void add(NrhpProperty p)
	{
		prop_map.put(p.getId(), p);
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
		
		addHelper(s, p);
		
		//add substrings
		int len = s.length();
		for (int i=1; i<len; i++) {
			addHelper(s.substring(i, len), p);
		}
	}
	
	private void addHelper(String text, NrhpProperty p) {
		prop_trie.put(text, p);
		/*HashSet<Integer> set = prop_trie.get(text);
		if (set == null) {
			set = new HashSet<Integer>();
			set.add(p.getId());
			prop_trie.put(text, set);
		}
		if (set.size() < 10) return;
		set.add(p.getId());
		*/
	}
	
	public List<NrhpProperty> search(String text){
		String s = text.toLowerCase();
		HashSet<NrhpProperty> set = new HashSet<NrhpProperty>();
		
		for( String key : prop_trie.keysWithPrefix(s)){
			set.add(prop_trie.get(key));
			if (set.size() > 100) break;
		}
		
		/*for( String key : prop_trie.keysWithPrefix(s)){
			for(Integer p:  prop_trie.get(key)){
				set.add(prop_map.get(p));
			}
		}*/
		
		return new ArrayList<NrhpProperty>(set);
	}
}

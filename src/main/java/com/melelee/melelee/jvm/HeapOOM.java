package com.melelee.melelee.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆 OOM
 *
 * @author mengll
 * @create 2019-03-15 14:33
 **/
public class HeapOOM {


	public static void main(String[] args) {
		List<byte[]> oomObjectList = new ArrayList<>();
		oomObjectList.add(null);
		while (true) {
			byte[] bytes = new byte[1 * 1024 * 1020];
			oomObjectList.add(bytes);
			System.out.println(oomObjectList.size());
		}
	}
}

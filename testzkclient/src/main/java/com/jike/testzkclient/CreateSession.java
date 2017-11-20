package com.jike.testzkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class CreateSession {

	public static void main(String[] args) throws Exception {
		ZkClient zc = new ZkClient("39.104.49.97:2181",10000,10000,new SerializableSerializer());
		System.out.println("conneted ok!");
	}
	
}

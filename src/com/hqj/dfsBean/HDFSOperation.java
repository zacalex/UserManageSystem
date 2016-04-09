package com.hqj.dfsBean;

//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
//import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;


public class HDFSOperation {
	private	Configuration conf;
	private FileSystem fs;
	
	public HDFSOperation() throws IOException{
		conf = new Configuration();
		fs = FileSystem.get(conf);
	}
	
	public boolean upLoad(InputStream in, String hdfsPath){
		Path p = new Path(hdfsPath);
		try{
			if(fs.exists(p)){
				System.out.println("file already exist");
				return false;
			}
			//�ϴ���ӡ�������
			Progressable progress = new Progressable(){
				public void progress() {					
					System.out.print(".");
				}			
			};
			FSDataOutputStream out = fs.create(p,progress);
			IOUtils.copyBytes(in, out, conf);
			
			byte[] buffer = new byte[400];
			int length = 0;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			out.flush();
			out.close();
			in.close();		
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return true;
	}
	/*
	 * �ļ���������ز���
	 * @param localPath �����ļ�����·��
	 * @param hdfsPath ָ�����ϴ��ļ���Hadoop�е�·��
	 * @return  �ļ��Ƿ����سɹ�
	 */
	public boolean downLoad(String hdfsPath,String localPath ){
		Path path = new Path(hdfsPath);
		try {
			if(!fs.exists(path)){
				System.out.println("file not exist");
				return false;
			}
			FSDataInputStream in =  fs.open(path);
			OutputStream out = new FileOutputStream(localPath);
			byte[] buffer = new byte[400];
			int length = 0;
			while((length = in.read(buffer))>0){
				out.write(buffer, 0, length);
			}
			
			out.close();
			in.close();	
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	/*
	 * �ļ������ɾ������	 
	 * @param hdfsPath ָ�����ϴ��ļ���Hadoop�е�·��
	 * @return  �ļ��Ƿ�ɾ���ɹ�
	 * @throws IOException IO�쳣
	 */
	public boolean deletePath(String hdfsPath){
		try {
			fs.delete(new Path(hdfsPath), true);
		} catch (IOException e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ȡ��ָ��Ŀ¼�µ��ļ����ļ����б�
	 * @param hdfsPath ָ�����ϴ��ļ���Hadoop�е�·��
	 * @return �ļ��б�
	 * @throws IOException IO�쳣
	 */
	public ArrayList<FileBean> getFileList(String hdfsPath){
		Path path = new Path(hdfsPath);
		ArrayList<FileBean> fileList = new ArrayList<FileBean>();
		FileStatus[] status;
		try {
			status = fs.listStatus(path);
			for(FileStatus fs : status){
				FileBean filebean=new FileBean();
				filebean.dfsBean(fs);
				fileList.add(filebean);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return fileList;
		
	}

}

/**
 * 
 */
package com.hqj.dfsBean;
import org.apache.hadoop.fs.FileStatus;

/** 
 *  
 * 
 */
public class FileBean {
		
		private String hdfsPath;
		private long fileSize;
		private long modifyTime;	
		private String fileName;
		private String fileDir;
		private boolean isDir;
	/*
	 * �ļ������вι��캯��
	 * @param fileStatus Hadoop�ļ�״̬����	
	 */
	public void dfsBean(FileStatus fileStatus){
		hdfsPath = fileStatus.getPath().toString();
		fileSize = fileStatus.getLen();
		modifyTime = fileStatus.getModificationTime();
		fileName = fileStatus.getPath().getName();
		fileDir = fileStatus.getPath().getParent().getName();
		isDir = fileStatus.isDirectory();
	}	
	/*
	 * �õ��ļ������HDFS·��	 * 
	 */
	public String getHdfsPath() {
		return hdfsPath;
	}
	/*
	 * �õ��ļ������HDFS��С	 * 
	 */
	public long getFileSize() {
		return fileSize;
	}
	/*
	 * �õ��ļ�������޸�ʱ��	 * 
	 */
	public long getModifyTime() {
		return modifyTime;
	}
	/*
	 * �õ��ļ����������	 * 
	 */
	public String getFileName() {
		return fileName;
	}
	/*
	 * �õ��ļ������HDFS·��	 * 
	 */
	public String getFileDir() {
		return fileDir;
	}
	/*
	 * �õ��ļ�������Ƿ���Ŀ¼	 * 
	 */
	public boolean isDir() {
		return isDir;
	}
	
	public String toString(){
		return hdfsPath;
	}
}
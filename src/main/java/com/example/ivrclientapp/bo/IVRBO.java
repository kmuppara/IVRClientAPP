package com.example.ivrclientapp.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ivrclientapp.vo.FileVO;

@Service
public interface IVRBO {
	
	public String fileUpload(FileVO fileVO) throws Exception;
	
	public FileVO fileDetails(String fileNm) throws Exception;
	
	public List<String> getFiles() throws Exception;

}

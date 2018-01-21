package com.example.ivrclientapp.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ivrclientapp.vo.FileVO;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

@Service
public class IVRBOImpl implements IVRBO {
	
	private String uri = "http://localhost:8086/ivrservice";
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public String fileUpload(FileVO fileVO) throws Exception {
		// .wav to .mp3 conversion start
		File source = fileVO.getFile();
		String name = fileVO.getFileNm();
		name = name.replaceAll("wav", "mp3");
		fileVO.setFileNm(name);
		File target = new File(name);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(128000));
		audio.setChannels(new Integer(1));
		audio.setSamplingRate(new Integer(8000));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		Encoder encoder = new Encoder();
		encoder.encode(source, target, attrs);
		// .wav to .mp3 conversion end
		fileVO.setFile(target);
		return fileUploadImpl(fileVO);
	}
	
	private String fileUploadImpl(FileVO fileVO) throws Exception{
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.ALL_VALUE);
		httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<FileVO> httpEntity = new HttpEntity<FileVO>(fileVO, httpHeaders);
		ResponseEntity<?> re = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
		if(re.getStatusCode() == HttpStatus.OK) {
			return "UPLOADED";
		}
		else throw new Exception("INTERNAL SERVER ERROR");
	}

	@Override
	public FileVO fileDetails(String fileNm) throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.ALL_VALUE);
		httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(fileNm, httpHeaders);
		String url = uri + "/" + fileNm;
		ResponseEntity<?> re = restTemplate.exchange(url, HttpMethod.GET, httpEntity, FileVO.class);
		FileVO fileVO = (FileVO) re.getBody();
		return fileVO;
	}

	@Override
	public List<String> getFiles() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.ALL_VALUE);
		httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);
		String url = uri + "/ivrfiles";
		ResponseEntity<?> re = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
		List<String> fileNms = (ArrayList<String>) re.getBody();
		return fileNms;
	}
	
}

/**
 * 
 */
package com.bhushan.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bhushan patil
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

	private LocalDateTime timestamp;

	private String fileName;
	
	private String url;

	private String message;

	private boolean status;

}

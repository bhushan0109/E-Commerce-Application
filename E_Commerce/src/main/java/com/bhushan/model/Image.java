/**
 * 
 */
package com.bhushan.model;

import javax.persistence.Embeddable;

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
@Embeddable
public class Image {

	private String imageName;

	private String imageUrl;

}

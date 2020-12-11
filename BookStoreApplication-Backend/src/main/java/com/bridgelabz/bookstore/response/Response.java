/**
 * 
 */
package com.bridgelabz.bookstore.response;

import java.util.List;

/**
 * @author HP
 *
 */
public class Response {

		private String message;
		private int statusCode;
		private Object obj;
		List<String>details;
		public Response(String message, int statusCode) {

			this.message = message;

			this.statusCode = statusCode;
		}

		public Response(String message, int statusCode, Object obj) {
			this.message = message;
			this.statusCode = statusCode;
			this.obj=obj;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public Object getObj() {
			return obj;
		}

		public void setObj(Object obj) {
			this.obj = obj;
		}

		public List<String> getDetails() {
			return details;
		}

		public void setDetails(List<String> details) {
			this.details = details;
		}

}

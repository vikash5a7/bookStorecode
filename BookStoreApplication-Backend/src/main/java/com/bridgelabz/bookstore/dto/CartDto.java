package com.bridgelabz.bookstore.dto;


public class CartDto {

	private Long quantityId;
	private Long quantityOfBook;
	private Double eachPrice;
	public Long getQuantityId() {
		return quantityId;
	}
	public void setQuantityId(Long quantityId) {
		this.quantityId = quantityId;
	}
	public Long getQuantityOfBook() {
		return quantityOfBook;
	}
	public void setQuantityOfBook(Long quantityOfBook) {
		this.quantityOfBook = quantityOfBook;
	}
	public Double getEachPrice() {
		return eachPrice;
	}
	public void setEachPrice(Double eachPrice) {
		this.eachPrice = eachPrice;
	}

	//@ApiModelProperty(notes = "The TotalPrice of the Book", required = true)// for swagger property mention...
}

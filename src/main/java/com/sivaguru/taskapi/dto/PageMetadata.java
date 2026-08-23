package com.sivaguru.taskapi.dto;

public class PageMetadata {

  private final int pageNumber;
  private final int pageSize;
  private final long totalElements;
  private final int totalPages;

  public PageMetadata(int pageNumber, int pageSize, long totalElements, int totalPages) {
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public int getPageSize() {
    return pageSize;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public int getTotalPages() {
    return totalPages;
  }

}

package com.sivaguru.taskapi.dto;

import com.sivaguru.taskapi.generics.ApiResponse;

public class PagedResponse<T> extends ApiResponse<T> {

  private final PageMetadata pageInfo;

  public PagedResponse(int statusCode, String message, T data, PageMetadata pageInfo) {
    super(statusCode, message, data);
    this.pageInfo = pageInfo;
  }

  public PageMetadata getPageInfo() {
    return pageInfo;
  }
}

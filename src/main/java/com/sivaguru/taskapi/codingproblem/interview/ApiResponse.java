package com.sivaguru.taskapi.codingproblem.interview;

public class ApiResponse<T> {

  int statusCode;
  String statusMessage;
  T data;

  public ApiResponse(int statusCode, String statusMessage, T data) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public T getData() {
    return data;
  }

}

package com.iconicsbooking.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ApiErrors {
LocalDateTime timestamp;
String message;
int status;
String error;

}

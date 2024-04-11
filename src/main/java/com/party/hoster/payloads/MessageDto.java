package com.party.hoster.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class MessageDto {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Long id;
    private UserDto sender;
    private UserDto reciever;
    
    @NotNull
    @NotBlank(message = "message content must not be blank")
    private String content;
    private LocalDateTime timestamp;

   
}

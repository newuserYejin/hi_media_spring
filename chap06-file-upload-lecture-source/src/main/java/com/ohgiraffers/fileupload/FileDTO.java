package com.ohgiraffers.fileupload;

import lombok.*;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO {

    private String originFileName;
    private String UUIDFileName;
    private String filePath;
    private String description;



}

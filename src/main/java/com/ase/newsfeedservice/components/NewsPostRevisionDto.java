package com.ase.newsfeedservice.components;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionType;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsPostRevisionDto {
    private int revisionNumber;
    private Date revisionTimestamp;
    private RevisionType revisionType;
    private NewsPost newsPost;
}

package com.example.greengram2.feed.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedCommentDelInsPdto {
  private int iuser;
  private int ifeed;
  private String comment;
  private int iFeedComment;
}

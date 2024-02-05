//package com.techsteed.TaskManager.model;
//
//import jakarta.persistence.*;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//
//public class TaskHistory {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long historyId;
//
//    @Column(nullable = false)
//    private Long userId;
//
//    @Column(nullable = false)
//    private Long taskId;
//
//    @Column(nullable = false, length = 50)
//    private String title;
//
//    @Column(length = 10)
//    @Value("LOW")
//    private String priority;
//
//    @Column(nullable = false)
//    private Date dueDate;
//
//    @Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(nullable = false)
//    private String createdBy;
//
//    @Value("false")
//    private Boolean taskStatus;
//}

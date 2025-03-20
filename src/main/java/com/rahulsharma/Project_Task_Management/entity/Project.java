package com.rahulsharma.Project_Task_Management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
//    private String createdBy;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id" , nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "project" , cascade = CascadeType.ALL)
    private List<Task> tasks;
}

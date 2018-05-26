package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldFetchAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> fetchTasks = dbService.getAllTasks();

        //Then
        assertEquals(1, fetchTasks.size());
        assertEquals("title", fetchTasks.get(0).getTitle());
        assertEquals("content", fetchTasks.get(0).getContent());
    }

    @Test
    public void shouldFetchTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        Optional<Task> optionalTask = Optional.of(task);

        when(taskRepository.findById(anyLong())).thenReturn(optionalTask);
        //When
        Optional<Task> fetchTask = dbService.getTaskById(1L);

        //Then
        assertNotNull(fetchTask);
        assertEquals(Long.valueOf(1L), fetchTask.get().getId());
        assertEquals("title", fetchTask.get().getTitle());
        assertEquals("content", fetchTask.get().getContent());
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task fetchTask = dbService.saveTask(task);
        //Then
        assertEquals(Long.valueOf(1L), fetchTask.getId());
        assertEquals("title", fetchTask.getTitle());
        assertEquals("content", fetchTask.getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task fetchTask = dbService.saveTask(task);
        dbService.deleteTask(task.getId().longValue());
        //Then
        assertEquals(0,dbService.getAllTasks().size());
    }

    @Test
    public void getTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");
        Optional<Task> optionalTask = Optional.of(task);

        when(taskRepository.findById(anyLong())).thenReturn(optionalTask);
        //When
        Optional<Task> fetchTask = dbService.getTask(1L);

        //Then
        assertNotNull(fetchTask);
        assertEquals(Long.valueOf(1L), fetchTask.get().getId());
        assertEquals("title", fetchTask.get().getTitle());
        assertEquals("content", fetchTask.get().getContent());
    }
}
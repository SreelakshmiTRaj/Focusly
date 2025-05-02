package com.example.Focusly.search;

import com.example.Focusly.studyplan.StudyPlan;
import com.example.Focusly.studyplan.StudyPlanRepository;
import com.example.Focusly.studytask.StudyTask;
import com.example.Focusly.studytask.StudyTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:5173")
public class SearchController {

    @Autowired
    private StudyTaskRepository taskRepo;

    @Autowired
    private StudyPlanRepository planRepo;

    @GetMapping
    public Map<String, List<?>> search(@RequestParam String query) {
        String q = query.toLowerCase();

        List<StudyTask> tasks = taskRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q);
        List<StudyPlan> plans = planRepo.findByGoalContainingIgnoreCase(q);

        Map<String, List<?>> results = new HashMap<>();
        results.put("tasks", tasks);
        results.put("studyPlans", plans);

        return results;
    }
}

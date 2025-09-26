package com.ase.newsfeedservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@Component
@Profile("dev") // Only runs when the 'dev' profile is active
public class MockDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(MockDataInitializer.class);

    @PostConstruct
    public void runMockDataScript() {
        try {
            // Find the script in the classpath resources
            ClassPathResource resource = new ClassPathResource("scripts/mock_data.py");
            File scriptFile = resource.getFile();

            logger.info("🐍 Executing Python script for mock data: {}", scriptFile.getAbsolutePath());

            // Use ProcessBuilder to run the script
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptFile.getAbsolutePath());
            processBuilder.redirectErrorStream(true); // Combine stdout and stderr

            Process process = processBuilder.start();

            // Log the script's output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logger.info("[Python Script]: {}", line);
                }
            }

            // Wait for the script to finish and check the exit code
            boolean finished = process.waitFor(60, TimeUnit.SECONDS);
            if (!finished) {
                process.destroy();
                throw new RuntimeException("Python script timed out.");
            }

            int exitCode = process.exitValue();
            if (exitCode == 0) {
                logger.info("✅ Mock data script executed successfully.");
            } else {
                logger.error("❌ Mock data script failed with exit code: {}", exitCode);
            }

        } catch (Exception e) {
            logger.error("Failed to execute Python script.", e);
        }
    }
}
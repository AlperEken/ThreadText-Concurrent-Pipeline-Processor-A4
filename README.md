# ThreadText: Concurrent Pipeline Processor

## Key Features

ThreadText: Concurrent Pipeline Processor is a high-efficiency text manipulation engine designed to demonstrate the power of asynchronous data processing through a multi-stage pipeline architecture. The system orchestrates three independent execution threads—a Reader, a Modifier, and a Writer—that work in parallel to stream data from a source file to a destination. As the text flows through the pipeline, the system applies dynamic transformations, such as real-time pattern matching and string replacement, ensuring that the end-to-end processing remains synchronized despite the varying computational speeds of each stage. Organisers can utilize the integrated dashboard to configure processing rules and monitor the operational status of each buffer stage, providing full visibility into the concurrent transformation lifecycle.

## Technical Implementation

This application is built using a decoupled Model-View-Controller (MVC) architecture, specifically engineered to handle complex thread interdependencies within a Java environment. The architectural backbone is a thread-safe `SharedBuffer` implementation that utilizes intrinsic locking and `wait/notify` signaling to manage the flow of character data between stages, effectively neutralizing the risks of buffer overflow and underflow. Each stage of the pipeline functions as an independent `Runnable` unit: the `Reader` manages I/O stream extraction, the `Modifier` performs intensive string manipulation using advanced pattern matching logic, and the `Writer` handles persistent data storage. This modular pipeline ensures that I/O operations and computational transformations occur asynchronously, maximizing system throughput.

## Challenges & Reflection

The most significant technical challenge of this project involved the coordination of the `Modifier` stage, which requires precise character-by-character analysis to detect multi-character search strings without disrupting the pipeline flow. Implementing a synchronized buffer that maintains state consistency across three separate access points required a rigorous understanding of monitors and atomic signaling protocols to prevent deadlocks. Furthermore, integrating a responsive GUI to display real-time status updates from background worker threads highlighted the complexities of thread-safe UI management. These challenges emphasized the value of defensive programming and refined the ability to architect systems where data integrity is preserved across multiple stages of asynchronous transformation.

## Getting Started

To initialize the ThreadText pipeline on your local machine, ensure the JDK is installed and execute the following commands in your terminal:

```bash
# Navigate to the source root directory
cd src

# Compile the modular pipeline and GUI components
javac textEditors/main/*.java textEditors/model/*.java textEditors/view/*.java

# Run the application through the main entry point
java textEditors.main.Main
```
*Author: Alper Eken Course: Concurrent Programming Semester: Spring 2025*

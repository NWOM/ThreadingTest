# ThreadingTest
# Web Server Performance Testing

## Project Overview

This project aims to test the performance of various threading mechanisms in a web server implemented in Java. The project demonstrates the performance differences between single-threaded, multi-threaded, and thread pool-based server architectures. Performance testing is conducted using Apache JMeter, showing the performance ranking as follows:
1. Thread Pool > Multi-threaded > Single-threaded

## Architectures

### Single-threaded Server

In a single-threaded server, the server handles one client request at a time. This means that each request must wait for the previous one to complete, leading to potential performance bottlenecks when multiple clients are connected simultaneously.

### Multi-threaded Server

A multi-threaded server creates a new thread for each incoming client request. This allows the server to handle multiple requests concurrently, significantly improving performance compared to a single-threaded server. However, creating a new thread for each request can lead to high resource consumption and overhead.

### Thread Pool-based Server

A thread pool-based server maintains a pool of pre-created threads. Instead of creating a new thread for each request, the server reuses existing threads from the pool. This approach reduces the overhead of thread creation and destruction, leading to better performance and resource management.

## Directories

- `singlethreaded/`: Contains the implementation of a basic single-threaded server and client.
- `multithreaded/`: Contains the implementation of a multi-threaded server.
- `ThreadPool/`: Contains the implementation of a thread pool-based server.

## Setup

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache JMeter (for performance testing)

### Building the Project

1. Clone the repository:
    ```sh
    git clone <repository_url>
    cd <repository_name>
    ```

2. Compile the project:
    ```sh
    javac -d bin src/singlethreaded/*.java
    javac -d bin src/multithreaded/*.java
    javac -d bin src/ThreadPool/*.java
    ```

## Running the Servers

### Single-threaded Server

1. Navigate to the `bin` directory:
    ```sh
    cd bin
    ```

2. Start the single-threaded server:
    ```sh
    java single_threaded.Server
    ```

3. Run the client to test the server:
    ```sh
    java single_threaded.Client
    ```

### Multi-threaded Server

1. Navigate to the `bin` directory:
    ```sh
    cd bin
    ```

2. Start the multi-threaded server:
    ```sh
    java multithreaded.Server
    ```

3. Run the client to test the server:
    ```sh
    java multithreaded.Client
    ```

### Thread Pool-based Server

1. Navigate to the `bin` directory:
    ```sh
    cd bin
    ```

2. Start the thread pool-based server:
    ```sh
    java ThreadPool.ThreadPoolServer
    ```



## Performance Testing with Apache JMeter

### Setting Up JMeter

1. Open Apache JMeter.
2. Create a new test plan.
3. Add a Thread Group and configure the number of threads, ramp-up period, and loop count.
4. Add an HTTP Request Sampler to the Thread Group and configure the server details (e.g., localhost, port).
5. Add a Listener (e.g., Summary Report, Graph Results) to visualize the performance metrics.
6. Run the test plan for each server implementation (single-threaded, multi-threaded, and thread pool-based).
7. Compare the results to analyze the performance.

### Performance Results

After conducting the performance tests using Apache JMeter, the results show the following ranking in terms of performance:
1. Thread Pool-based Server
2. Multi-threaded Server
3. Single-threaded Server


## Conclusion

This project demonstrates the impact of different threading mechanisms on the performance of a web server. The results highlight the advantages of using a thread pool-based architecture for handling multiple client requests efficiently.

## Future Work

- Implement asynchronous I/O for further performance improvements.
- Integrate a more sophisticated request handling mechanism.
- Extend performance testing to include various load scenarios and metrics.

## Acknowledgements

- [Java Documentation](https://docs.oracle.com/javase/8/docs/)
- [Apache JMeter](https://jmeter.apache.org/)

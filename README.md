# P3-ExternalSort
For this project, you will implement an external sorting algorithm for binary data. The input data file will consist of at least 8 blocks of data, where a block is 8,192 bytes. Each block will contain a series of records, where each record is 16 bytes long and contains two fields. The first 8-byte field is a non-negative integer value of type long for the record ID and the second 8-byte field is the record key of type double, which will be used for sorting. Thus, each block contains 512 records (8192 bytes/16 bytes per record = 512 records per block).  

Your program must sort the records in the file, in ascending order of the key values, using replacement selection, as described in the OpenDSA book in Canvas. Your program will sort sections of the file in a working memory that is 8 blocks long. To be precise, the heap will be 8 blocks in size; in addition you will also have a one block input buffer, a one-block output buffer and any additional working variables that you need. The section in OpenDSA on heapsort can provide useful suggestions on how to implement the heapsort.   

To process the data, read the first 8 blocks of the input file into working memory and use replacement selection to create the longest possible run. As it is being created, the run is output to the one block output buffer. Whenever this output buffer becomes full, it is written to an output file called the run file. When the first run is complete, continue on to the next section of the input file, adding the second run to the end of the run file. When the process of creating runs is complete, the run file will contain some number of runs, each run being at least 8 blocks long, with the data sorted within each run. For convenience, you will probably want to begin each run in a new block in the output file (i.e. if the run does not end exactly at a block-boundary, advance to the start of the next block in the file). You will then use a multi-way merge to combine the runs into a single sorted file.  

You must also use the 8 blocks of memory used for the heap in the run-building step to store working data from the runs during the merge step. Multi-way merging is done by reading the first block from each of the runs currently being merged into your working area, and merging these runs into the one block output buffer. When the output buffer fills up, it is written to another output file. Whenever one of the input blocks is exhausted, read in the next block for that particular run. This step requires random access (using `seek()`) to the run file, and a sequential write of the output file. Depending on the size of all records, you may need multiple passes of multiway-merging to sort the whole file.

Java's `ByteBuffer` class is useful for serializing and deserializing the record.


## *Important Links* 
- [ByteBuffer](https://docs.oracle.com/javase/7/docs/api/java/nio/ByteBuffer.html)
- [Project 3 Specs](https://canvas.vt.edu/courses/145371/pages/project-3-specification)
- [Project 3 Assignment Location](https://canvas.vt.edu/courses/145371/assignments/1455578)

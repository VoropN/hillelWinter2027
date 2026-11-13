# Duplicate File Finder — Implementation Report

`Program` recursively scans one or more directory roots and groups regular files by size before calculating CRC32. It hashes only sizes that occur more than once, then reports groups with matching size and CRC32. This finds identical-content files even when their names differ. CRC32 is calculated using a buffered stream, so files are not loaded entirely into memory. Progress is written to standard error during scanning and hashing. Overlapping roots do not add the same normalized path more than once.

Run the program with one or more directory paths. It prompts for the maximum file size in MiB; enter `0` to disable the limit. The limit is inclusive:

```text
java findFileDuplicate.base.Program /path/to/first /path/to/second
```

With no directory arguments, the program prompts for one directory path. Read and traversal errors are reported rather than silently ignored.

`ProgramTest` creates a temporary directory tree and verifies that identical files with different names are reported, while same-size files with different content and same-name files with different sizes are excluded. It also checks scanning multiple roots and the inclusive size limit.

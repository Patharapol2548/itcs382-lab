| No. | Check | Code location (file:line) | Finding | Severity (Must fix, Should fix or Note) | Suggested fix |
| --- | --- | --- | --- | --- | --- |
| 1 | Check 1 | not found | Probable bug: Unclosed resources were not found (`Scanner` in `Main.java:11` and `FileWriter` in `CsvRideRepository.java:15` are properly enclosed in try-with-resources). | Note | Maintain try-with-resources for all AutoCloseable resources. |
| 2 | Check 1 | not found | Probable bug: Objects compared with `==` instead of `equals()` were not found. | Note | Continue using `.equalsIgnoreCase()` and `.equals()` for string comparisons. |
| 3 | Check 1 | not found | Code smell: Unused imports, fields, or parameters were not found. | Note | Keep import statements and member declarations clean. |
| 4 | Check 1 | not found | Code smell: Empty blocks were not found (exceptions are reported to `System.err`). | Note | Continue providing proper handling in all catch blocks. |
| 5 | Check 1 | `Main.java:23` | Code smell: The string literal `"rent"` (and `"return"` at line 25) is repeated between command dispatch and usage strings. | Note | Define constants for CLI command keyword literals. |
| 6 | Check 1 | not found | Method complexity: Methods that are too long or too complex to read easily were not found. | Note | Keep methods small, focused, and readable. |
| 7 | Check 2 | `Main.java:7` | Method cohesion: `main` exhibits procedural cohesion because operations are grouped by the sequential order of reading, parsing, and dispatching commands. | Note | Maintain modular command handling by delegating actions to specialized helper methods. |
| 8 | Check 2 | not found | Class cohesion: Weak class cohesion was not found (each class has a single responsibility and operates on its own attributes). | Note | Keep domain rules, persistence, and CLI presentation separated into distinct classes. |
| 9 | Check 2 | not found | Coupling: Content coupling was not found (all fields across all classes and records are private). | Note | Maintain strict encapsulation with private fields. |
| 10 | Check 2 | not found | Coupling: Common coupling was not found (no public static variables or shared global state exist). | Note | Avoid global mutable state in future extensions. |
| 11 | Check 2 | not found | Coupling: Control coupling was not found (no boolean flags or control opcodes alter internal method execution). | Note | Continue defining distinct methods for distinct domain operations. |
| 12 | Check 2 | `Main.java:24` | Coupling: Stamp coupling because `handleRent` (and `handleReturn` at line 26) receives the entire `String[] parts` array when it only requires specific token values. | Should fix | Pass only the individual required string values instead of the entire array. |
| 13 | Check 2 | `CsvRideRepository.java:14` | Coupling: Stamp coupling because `save` receives the composite `RideRecord` data structure. | Note | Retain the record parameter as it represents a cohesive domain value object for persistence. |
| 14 | Check 2 | not found | Strong coupling: Classes depending on many other classes or reaching through objects to call others (Law of Demeter violations) were not found. | Note | Continue interacting with classes through shallow method calls and interfaces. |
| 15 | Check 2 | `BikeShareService.java:19` | Connascence: Connascence of Convention because station names (`"Library"`, `"Canteen"`, `"Dorm"`) are represented by arbitrary case-sensitive String literals across classes. | Should fix | Introduce a `Station` enum to weaken connascence to name and ensure compile-time validity. |
| 16 | Check 2 | `RideRecord.java:3` | Connascence: Connascence of Position because callers must supply constructor arguments in the exact positional order `(bike, minutes, fee)`. | Note | Maintain the record definition or provide a builder if the parameter count increases. |
| 17 | Check 2 | not found | Connascence: Connascence of Algorithm between classes was not found. | Note | Keep business algorithms encapsulated within their respective domain entities. |
| 18 | Check 2 | not found | Encapsulation: Fields that are not private were not found (all fields in all classes and records are private). | Note | Maintain private visibility for all instance attributes. |
| 19 | Check 2 | not found | Encapsulation: Operations returning internal mutable collections were not found. | Note | Keep collections encapsulated within domain services without leaking references. |

### Number of findings for each check:
- **Check 1 (Code quality):** 6 findings (1 minor smell noted, 5 confirmed not found)
- **Check 2 (Cohesion, coupling and connascence):** 13 findings (4 design observations noted / should fix, 9 confirmed not found)
# Mini-Project 2 : Fun with Fractions
## Name(s)
Julian Kim

## Description
This project includes my work for - [MP02: Fun with Fractions](https://rebelsky.cs.grinnell.edu/Courses/CSC207/2023Fa/mps/mp02.html).

## Resources used
- None

## Rubric (for personal use, not actual rubric)
<details>

<summary> Expand </summary>
    
### Redo or above
- [x] Includes the three specified `.java` files, correctly named.
- [x] Each class has an introductory Javadoc comment that indicates
    the author and purpose. 
- [x] Includes a `README.md` file.
- [x] The `README.md` file contains the appropriate information (authors,
    purpose, acknowledgements if appropriate)
- [x] All files compile correctly.
- [x] `QuickCalculator` and `InteractiveCalculator` run.
- [x] `BFCalculator` includes the two required non-static methods,
    `evaluate` and `store`.
### Meets expectations or above
- [x] Appears to follow Google Java style guidelines for indentation
    and such. (*Used VSCode's built-in formatter to ensure*)
- [x] Fractions always appear in simplest form.
- [x] Handles expressions with two fractions and one operation. 
- [x] Handles expressions with one fraction, one register, and one operation.
- [x] All data are stored in class fields, so that we can have two
   `BFCalculator` objects running simultaneously and they will not
   interfere with each other.
### Exemplary / Exceeds expectations
- [x] All (or most) repeated code has been factored out into individual
    methods.  It is *not* necessary that common code between 
    `QuickCalculator` and `InteractiveCalculator` be factored out into
    a separate class. (*Probably compliant?*)
- [x] All or most variable names are appropriate. (*Again, probably.*)
- [x] Handles expressions without fractional parts, such as `2 + 123`. (*Yeah, it does, but it converts it to an integer over 1.*)
- [x] Handles expressions with no operations, such as `a`, `11/2`, or `5`.
- [x] Handles negative numbers.
- [x] Provides an appropriate error message if the expression has the
    wrong form (e.g., two numbers/registers in a row or two operations
    in a row).

### Notes
- (9/14/23) Last 3 crash and burn! I thought we didn't need to handle those errors yet...
    Time to fix!
- (9/14/23) Fixed. Finally done!

</details>

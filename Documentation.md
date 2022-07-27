# Rules - Documentation {#documentationRules}

[Back to home](@ref mainpage)

## TL;DR: Keep things simple and concise

Create good documentation:

- by thinking documentation before code.
- by keeping documentation *simple and concise*.
- by keeping documentation *close to the code* and *close to the API*, which are the *ultimate truths* of your application.
- by keeping your documentation *DRY* (Don't Repeat Yourself).
- by making documentation *available to others*, through the *ticketing system*, *version control*, aka our GitHub Repo's Project.
- by *referencing ticket IDs* (issue and PR numbers) throughout your available media.
- by *forgetting about “external” documentation*, as long as you can.



## Documentation Tips

### 1. Think Documentation before Code

Thinking documentation before code will help you clarify your mind and lay out clearly what you need to achieve with the code. So the first step could be writing the following code:

```
/**
 * This class allows managing LUT by providing CRUD operations using LUT and 
 * acts as a singleton. In order to get the instance of this manager, the method 
 * {\@link #getInstance()} can be used. Then CRUD operations can be called like the following:
 * 

{\@link #addLUT(LUT)} in order to add a LUT that will be managed by the * singleton instance ;

* \@author Hady 
* \@version 2.0 
* \@since ADT 1.0
*/
public class LUTManager { }
```

Well this is a short example which:

- forces you to think what is the purpose of the class you are creating
- helps you identify your needs
- reminds you what you are doing, even after taking your break
- helps you estimate what is still to be done

### 2. You're on a Team

remember that doing documentation helps the team increase its productivity and focus on the development.



### 3. Applying the Javadoc coding standards

Javadoc is a key part of coding in Java, yet there is relatively little discussion of what makes good Javadoc style - a coding standard.

this guide below in the [Document References](#Documentation References) consists of a short description of the rule and an explanation, which may include an example.



#### In Javadoc, you have some tags which include the following:

- "\@author"
- "\@version"
- "\@param"
- "\@return"
- "\@exception" 
- "\@throws" 
- "\@see"
- "\@since"
- "\@serial"
- "\@serialField"
- "\@serialData"
- "\@deprecated"



#### 1. Use "\@link" and "\@linkplain" to point to Some Code.

refer to classes and methods if there is a dependency or if it is just useful for the documentation. In order to make it easier to navigate through methods and classes, you can use "\@link". It works like this:

- "{\@link LUTManager}" to point to a class
- "{\@link LUTManager LUT manager}" to point to a class with a given label
- "{\@link #read(LUT, boolean)}" to point to a method inside the same class
- "{\@link #read(LUT, boolean) read}" to point to a method inside the same class with a given label
- "{\@link LUTManagers#read(LUT, boolean)}" to point to a method inside another class
- "{\@link LUTManagers#read(LUT, boolean) LUT manager read}" to point to a method inside another class with a given label

The difference between `\@link` and `\@linkplain` is that the latter one doesn't produce a monospaced code font.



#### 2. Use \\@code for Code Snippets

Often you can find some code inside a Javadoc to illustrate how to use a method, class or to provide some other example. In order to display the code correctly and preventing some markup to be interpreted such as \<String\> and so on.


```
{\@code 
List LUTs = new ArrayList<>();
  for(int index = 0; index < 10; index++) {
    LUTs.add(new LUT("LUT #" + index)); 
  }
}
```


The `\@code` will generate the < pre > markup for you.

#### 3. Use \\@value to Insert the Value of a Field in the Documentation

When you have a constant, you may want to display its value in the documentation. So you have two options:

- Insert the value yourself. But if the value changes, you will have to update your documentation, and as you will not forget to do it, you can choose this option safely
- Use `\@value` which will insert the value for you, and you won't need to worry about updating your documentation

Well I will discuss the second option which is for me the best way to take advantage of the Javadoc tool. Indeed, using a single attribute is really helpful:

```
/*
  The default value for this field is {\@value}.
*/
public static final String LUT_NAME="Hady's  ";
```


But you can also refer to another constant, for example:

```
/*
  The default value for this field is {\@value} when the value 
  of {\@link #OWNER} is {\@value #OWNER}.
 */
public static final String LUT_NAME="Hady's  ";

/*
  The default owner of this awesome LUT  .
 */
public static final String OWNER=" Hady";
```

 

#### 4. Indicate when the Features Have Been Available With \\@since

It is often useful to indicate when a class or a method became available in your code. For this you use the `\@since` tag followed by the version/year since the feature or class has been implemented:

```
/**
 * This awesome class is for doing awesome things
 * \@since LUT-core-0.1
 * \@version 0.2
*/
public class LUTsManager {

  /**
   * Allows reading LUTs
   * \@since LUT-core-0.2
   */
  public void read(LUT LUT, boolean fast) {
    // TODO
  }
}
```


As you can see I use it on both methods and classes and not only with a version number. Indeed, nowadays, we have applications with multiple modules which can have different life cycle and so, versions. Saying that a class or a method are available since version 0.2 doesn't have a particular meaning. Version 0.2 of what? This is why I always put a relevant `\@since` for helping my teammates understand when something has been available at the first sight.

Moreover, I can identify an advantage of this tag which is helping you to build release notes. Wait, what? No, really, go to your favorite IDE, [IntelliJ IDEA](https://www.jetbrains.com/idea/) for instance, and search for files containing "\@since LUT-core-0.2" and now you can identify what has been added. This doesn't tell you what have been *updated* of course, only what's new. But you should agree, such a straightforward trick is useful.

#### 5. Don't Be Anonymous, Use \\@author

There is something I really dislike: people not owning their code and not indicating it is them, who have written this *awful code* for *an awful reason*. If you write code, own it, or go to being a manager. You have the `\@author` tag you can use to identify you're the author of a class or method. I think it is a good practice to put it on both classes and methods because the author of a class might not write all methods of it.

Another best practice is to put **all** authors for a class or method. Imagine you and your teammate have written an awesome method, and you're identified as the only author. And one day, when you are on vacation, someone is reading your wonderful method and doesn't understand it very well and would like some details. But as you're listed as the only author they don't know that the information is easily accessible from your colleague who worked on this code with you. You see where I'm going, don't you? Always document the code authorship with `\@author`.

#### 6. For Non-Void Methods, Always Use \\@return

Well this is one really hits home with me. Sometimes I read the code like on the example below and just cringe.

```
/* Get the address.
  \@return
 */
public String getAddress(){ /* … */ }
```


Why!? Really, why you don't fill the `\@return?` *“It says just 1 line below, get the address”*.

NO. NO. PLEASE NO. If you answer like that, it's because your documentation is, well, how can I say that, *subpar*! Yes, because instead of having the poor documentation like you just saw above, you can easily have a better version, look:

```
/*
  Get the address of this LUT  . The address is of the following format:
  {\@code address line 1
 * address line 2
 * zipcode city}
  \@return the address of this LUT   or {\@code null} if not filled.
 */
```


Much better, right? Here your Javadoc is useful. I always try to find a proper way of documenting the code because sometimes readers only read the `\@return`, others the text above, and you can easily avoid confusion by typing a couple more words.

#### 7. Clarify What Parameters Mean With \\@param

What is more frustrating than seeing a method that takes a parameter named something unclear, like *i* with no documentation? Sometimes you can guess the purpose of that parameter thanks to the method's name. Then again, sometimes you cannot. So in your documentation you should use `\@param` in order to indicate what this parameter means and, potentially, indicate what the valid values are. In our case, ***i*** could be the level of the log and the values will be INFO, DEBUG or TRACE. Another case where this tag is particularly useful is when the value corresponds to an index. In some case indexes start at 0 and in other at 1. `\@param` is the right tag to describe such differences.



## Basic Format of Documentation Blocks

### Documentation MUST be delimited in Javadoc style

Multi-line documentation blocks must begin with `/**` and end in `*/`. Single-line documentation blocks must begin with `///`. For consistency, do not begin documentation blocks with `/*!` or `//!`.

(*Note:* one-line documentation blocks are rarely used for public APIs, see [Common Structure of Documentation Blocks](https://developer.lsst.io/cpp/api-docs.html#cpp-doxygen-sections).)

Under certain circumstances, single-line documentation blocks may begin with `///<` instead of `///`. These cases are indicated below.

### Multi-line documentation delimiters SHOULD be on their own lines

A multi-line documentation block's summary sentence should occur on the line after the opening `/**`, and the terminating `*/` should be on its own line. An example:

```
/**
 * Sum numbers in a vector.
 *
 * This sum is the arithmetic sum, not some other kind of sum that only
 * mathematicians have heard of.
 *
 * \@param values Container whose values are summed.
 * \@return sum of `values`, or 0.0 if `values` is empty.
 */
```

### Documentation MUST use Javadoc-style tags

Documentation blocks must use tags such as `\@see` or `\@param` in place of `\see` or `\param`. This is both for internal consistency and to avoid conflicts with other tools that give special treatment to `\word`.



### Documentation SHOULD use Markdown for formatting

LSST uses [Markdown-formatted Doxygen comment blocks](http://www.doxygen.org/manual/markdown.html). If a particular format cannot be expressed using Markdown, you MAY use [Doxygen’s built-in formatting](http://www.doxygen.org/manual/commands.html) or, if necessary, [HTML markup](http://www.doxygen.org/manual/htmlcmds.html).



### Documentation MUST appear where a component is first declared

In general, this means documentation blocks will appear in header (`.h`) files rather than source (`.cc`) files. This keeps all the documentation with the API and avoids certain false alarms when Doxygen parses C++11 code.



### Documentation MUST appear before the declaration it describes, and with the same indentation

For example:

```
/**
 * Sum numbers in a vector.
 *
 * \@param values Container whose values are summed.
 * \@return sum of `values`, or 0.0 if `values` is empty.
 */
double sum(std::vector<double> & const values) {
    ...
}
```

Not:

```
double sum(std::vector<double> & const values) {
    /**
     * Sum numbers in a vector.
     *
     * \@param values Container whose values are summed.
     * \@return sum of `values`, or 0.0 if `values` is empty.
     */
    ...
}
```

## Common Structure of Documentation Blocks

We organize Doxygen comment blocks into sections that appear in a common order. This format is analogous to the one adopted for the [Python documentation](https://developer.lsst.io/python/numpydoc.html#py-docstring-sections). The sections and their relative order are:

1. [Short Summary](#Short-Summary)
2. [Extended Summary](#Extended-Summary) (recommended)
3. [Template Parameters](#Template-Parameters) (if applicable; for classes, methods, and functions)
4. [Function/Method Parameters](#Function/Method-Parameters) (if applicable; for methods and functions)
5. [Returns](#Returns) (if applicable; for methods and functions)
6. [Throws](#Throws) (if applicable; for methods and functions)
7. [Exception Safety](#Exception-Safety) (optional; for methods and functions)
8. [Helper Functions](#Helper-Functions) (if applicable; for functions)
9. [Initializer Declaration](#Initializer-Declaration) (optional; for constants)
10. [See Also](#See-Also) (optional)
11. [Notes](#Notes) (optional)
12. [References](#References) (optional)
13. [Examples](#Examples) (optional)

For summaries of how these sections are composed in specific contexts, see:

- [Documenting Classes and Type Aliases](#Documenting-Classes-and-Type-Aliases)
- [Documenting Enumerated Types](#Documenting-Enumerated-Types)
- [Documenting Methods and Functions](#Documenting-Methods-and-Functions)
- [Documenting Constants, Variables, and Data Members](#Documenting-Constants,-Variables,-and-Data-Members)



### Short Summary

A one-line summary that does not use variable names or the function's name. This summary will appear in lists of class/namespace members.

```
/// Sum two numbers.
double add(double a, double b);
```

By default, brief summaries will end at a period followed by whitespace, or at a new line, whichever comes first. You can ignore periods that shouldn't end the description by following them with a backslash and a space (as in `"e.g.\ "`).

Brief summaries should be short enough to fit on one line. If you must have a summary that extends over multiple lines, you must prefix the summary by `\@brief`, which will cause the summary to end at the next blank line rather than the next line break.

For functions and methods, the summary should be written in the imperative voice (i.e., as a command that the API consumer is giving). Getters and other methods that are more naturally described as values rather than actions may ignore this rule.



### Extended Summary

The extended summary is an optional sentence or short paragraph that clarifies and supports the [summary sentence](#Short-Summary). Taken together with the summary sentence, the summary content in general exists to help users quickly understand the role and scope of the API.

Leave detailed discussions of the API's features, usage patterns, background theory, and implementation details to the [Notes](#Notes) and [Examples](#Examples) sections. The [Parameters](#Function/Method Parameters) and [Returns](#Returns) sections are ideal places to discuss in detail individual parameters and returned values, respectively.

This section's brevity is critical. The extended summary is proximate to the summary sentence so that the two pieces of content support each other. However, the extended summary also separates the API signature from the [Parameters](#Function/Method Parameters) section, which users expect to see close together. As a general guideline, the extended summary should be three sentences or fewer.



### Template Parameters

A series of `\@tparam` tags, usually one for each template parameter. Each tag should have a description following the parameter name. You do *not* usually need to document default values; Doxygen will provide the default automatically. If the description extends over multiple lines, each line after the first must be indented.

Parameters should be listed in the same order as they appear in the class, function, or method signature.

```
/**
 * Storage for arbitrary data with log(N) lookup.
 *
 * ...
 *
 * \@tparam T the type of data stored in the table
 * \@tparam ComparatorT callable defining a strict weak ordering for objects
 *     of type `T`. Its `operator()` must accept two `T` and return `true`
 *     if and only if the first argument comes before the second. It must
 *     not throw exceptions.
 */
template <typename T, typename ComparatorT = std::less<T>>
class LookupTable
{
    ...
}
```

When two or more consecutive template parameters have *exactly* the same description, they can be combined:

```
/**
 * \@tparam T, U the types of the pair components
 */
```

Note

Doxygen will not properly parse parameter descriptions that have multiple paragraphs. If your template parameters require a lengthy explanation, put the explanation in the [Extended Summary](#Extended-Summary) and refer to it from the parameter descriptions.

#### Template Specializations

When a partial template specialization reuses parameters from the full template, there is no need to re-document each parameter. If you are omitting the parameters, the documentation must include a cross-reference to the full template, possibly as part of the [See Also](#See-Also) section.

You must re-document the parameters if the template specialization redefines any parameters (e.g., if the generic parameter `T` becomes `T*` in the specialization) or if it places additional restrictions on their values.



### Function/Method Parameters

A series of `\@param` tags, usually one for each parameter. Each tag should have a description following the parameter name. You do *not* usually need to document default arguments; Doxygen will provide the default automatically. If the description extends over multiple lines, each line after the first must be indented.

Parameters should be listed in the same order as they appear in the function or method signature. Make sure to keep the parameter list in sync with the actual parameters; Doxygen will issue a warning if they don't match.

`\@param` should be given with the `[in]`, `[out]`, or `[in, out]` tag if the function method contains any output parameters. The `[in]` tag is optional if all parameters are input, even if other functions or methods in the same class or package use output parameters.

```
/**
 * Compute mean and standard deviation for a collection of data.
 *
 * \@param[out] mean the mean of `data`, or `NaN` if `data` is empty
 * \@param[out] stdDev the unbiased (sample) standard deviation, or `NaN`
 *     if `data` contains fewer than 2 elements
 * \@param[in] data the data to analyze
 */
void computeStatistics(double & mean, double & stdDev, std::vector<double> const & data);
```

When two or more consecutive parameters have *exactly* the same description, they can be combined:

```
/**
 * \@param x, y the coordinates where the function is evaluated
 */
```

Note

Doxygen will not properly parse parameter descriptions that have multiple paragraphs. If your function's input requires a lengthy explanation, put the explanation in the [Notes](#Notes) and refer to it from the parameter descriptions.



#### Annotating Parameters with Inline Comments (historical)

An alternative to the `\@param` tag is to use an inline comment after each parameter, one per line. These comments are prefixed with `///<`.

This style is permitted for historical reasons, but should not be used in new code. If the parameter descriptions are too long to fit in a single line of source, the `\@param` documentation method *must* be used.



### Returns

A `\@returns` tag, followed by a description similar to the one for [Function/Method Parameters](#Function/Method Parameters). If the returned value is a map, ensure that the key-value pairs are documented in the description.

For consistency with Python documentation, always use `\@returns` and not the synonymous `\@return`.



### Throws

A series of `\@throws` tags, one for each type of exception. Each tag should have a description following the exception type. If the description extends over multiple lines, each line after the first must be indented.

```
/**
 * Write an image to disk.
 *
 * \@throws lsst::pex::exceptions::IoError Thrown if `fileName` could not be
 *     written to.
 */
void writeImage(std::string const & fileName);
```

Exception classes must be namespace-qualified using the same rules as [\@see](#See-Also). Doxygen will render one or more `\@throws` tags as a table of exceptions and descriptions, so do not treat `\@throws` as the first word of the description.

For consistency with Python documentation, always use `\@throws` and not the synonymous `\@throw` or `\@exception`.



### Exception Safety

Whether there are any `\@throws` tags for specific exceptions, a function or method should have an `\@exceptsafe` tag. The description following the tag should describe the level of exception safety provided by the function or method.

The following terms may be used for brevity:

- no-throw

  The function is guaranteed to always return without throwing an exception.

- strong

  If the function throws an exception, the program will be in the same state as before the call; i.e., failed calls have no side effects.

- basic

  If the function throws an exception, the program will be in a valid state, but not necessarily a predictable one. No memory, file descriptors, locks, or other resources will be leaked.

- none

  If the function throws an exception, objects may be corrupted and unsafe to use, or resources may be leaked.

Examples:

```
/**
 * Image associated with this map.
 *
 * \@exceptsafe Shall not throw exceptions.
 */
ImageF getImage() const noexcept;
/**
 * Apply a user-specified transformation to an image.
 *
 * \@exceptsafe If `transform` provides basic exception safety, then this
 *     method shall provide strong exception safety. Otherwise, it provides
 *     no exception safety guarantee.
 */
template <class Func>
ImageF transformImage(Func const & transform) const;
```



### Helper Functions

Some operations on a class, particularly arithmetic operators, must be implemented as standalone functions even though they are *conceptually* part of the class. These functions should have the `\@relatesalso` tag, followed by the name of the appropriate class. They will appear on the class's documentation page under the heading “Related Functions”. Use this tag sparingly.

For internal consistency, always use `\@relatesalso` and not the synonymous `\@relatedalso`.

Examples:

```
/**
 * Add two images pixel-by-pixel.
 *
 * \@relatesalso ImageF
 */
ImageF operator+(ImageF const & lhs, ImageF const & rhs);
```



### Initializer Declaration

By default, Doxygen shows the values of constants unless they are very long. The `\@showinitializer` and `\@hideinitializer` tags override this behavior.

```
/**
 * Maximum number of simultaneous readers supported.
 *
 * \@hideinitializer
 */
int const MAX_READERS = 16;    // Value is implementation detail and subject to change
```



### See Also

‘See Also' is an optional section used to refer to related code. This section can be very useful, but should be used judiciously. The goal is to direct users to other functions they may not be aware of, or have easy means of discovering (by looking at the class or package documentation, for example). Functions whose documentation further explains parameters used by this function are good candidates.

This section can also refer to arbitrary pages using a URL or a Markdown-style link.

List each class, function, method, or link using a `\@see` tag:

```
/**
 * Compute an element-wise cosine.
 *
 * \@see sin
 * \@see tan
 * \@see [numpy.vectorize](https://docs.scipy.org/doc/numpy/reference/generated/numpy.vectorize.html)
 */
vector<double> cos(vector<double> const & angles);
```

Prefix objects from other namespaces appropriately by their greatest common namespace. For example, while documenting an `lsst::afw::tables` module, refer to a class in `lsst::afw::detection` by `afw::detection::Footprint`. When referring to an entirely different module or package, use the full namespace. Do not use namespace abbreviations, as Doxygen has trouble resolving them.

For internal consistency, always use `\@see` and not the synonymous `\@sa`.



### Notes

*Notes* is an optional section that provides additional conceptual information about the API.

The notes must be prefixed by a `\@note` or `\@warning` command. For internal consistency, always use `\@note` and not the synonymous `\@remark` or `\@remarks`.

Some things to include in a *Notes* section:

- Discussions of features, going beyond the level of the [summary sentence](https://developer.lsst.io/python/numpydoc.html#py-docstring-short-sum#Short-Summary) and [extended summary](#Extended-Summary).
- Usage patterns, like how code is expected to use this API, or how this API is intended to be used in relation to other APIs.
- Background theory. For example, if the API implements an algorithm, you can fully document the algorithm here.
- Implementation details and limitations, if those details affect the user's experience. Purely internal details should be written as regular code comments.

Specific how-tos, tutorials, and examples go in the [Examples section](#Examples) instead of *Notes*. The *Notes* section is dedicated to conceptual documentation.

The [Function/Method Parameters](#Function/Method Parameters) and [Returns](#Returns) sections are the best places to describe specific input and output variables. The *Notes* section can still reference these variables by name, and discuss how they work at a big-picture level. Since the content in the [Function/Method Parameters](#Function/Method Parameters) needs to be brief, you can write additional content as part of the *Notes* section.

This section may include mathematical equations to document the algorithm implemented by the function or class. Equations may be written in [LaTeX](http://www.latex-project.org/) format:

```
/**
 * The FFT is a fast implementation of the discrete Fourier transform:
 * \@f[ X(e^{j\omega } ) = x(n)e^{ - j\omega n} \@f]
 */
```

LaTeX's environments can also be used:

```
/**
 * The discrete-time Fourier time-convolution property states that
 * \@f{eqnarray*}
 * x(n) * y(n) \Leftrightarrow X(e^{j\omega } )Y(e^{j\omega } )\\
 * another equation here
 * \@f}
 */
```

Math can also be used inline:

```
/**
 * Fit a model of the form \@f$y = a x + b\@f$ to the data.
 */
```

Note that LaTeX is not particularly easy to read, so use equations judiciously. In particular, do not use inline LaTeX just to add Greek or other special symbols; prefer [HTML character entities](http://www.doxygen.org/manual/htmlcmds.html) or Unicode instead.

Doxygen recovers poorly from typos in formulas; you may need to manually delete `docs/html/formula.repository` if it contains a bad formula.

Images are allowed, but should not be central to the explanation; users viewing the documentation as text must be able to comprehend its meaning without resorting to an image viewer. These additional illustrations are included using the `\@image` command:

```
/**
 * \@image html filename ["caption"]
 */
```

`filename` is a path relative to the project root directory.

Equations or images may be used as described in Extended Summary.



### References

References can be included either in the [‘Notes'](#Notes) section or in a separate list below them. A reference consists of the `\@cite` tag, followed by a BibTeX label. BibFiles must be listed in the `CITE_BIB_FILES` configuration tag in `doc/doxygen.conf.in`.

Note that Web pages should be referenced with regular inline links.

References are meant to augment the documentation, but should not be required to understand it.



### Examples

‘Examples' is an optional section for examples. This section is very strongly encouraged.

Examples should use Markdown formatting for code blocks (i.e., indented by four extra spaces):

```
/**
 * This is an amazing function! For example:
 *
 *     auto cosines = cos(angles);
 *
 * Comment explaining the second example.
 *
 *     auto cosines = cos(radians(angles));
 */
```



## Documenting Classes and Type Aliases

Class documentation blocks are placed immediately before the class declaration, and serve to document the class as a whole rather than individual methods.

1. [Short Summary](#Short-Summary)
2. [Extended Summary](#Extended-Summary) (recommended)
3. [Template Parameters](#Template-Parameters) (if applicable; for classes, methods, and functions)
4. [See Also](#See-Also) (optional)
5. [Notes](#Notes) (optional)
6. [References](#References) (optional)
7. [Examples](#Examples) (optional)

For example:

```
/**
 * Implementation of a trace facility for LSST
 *
 * Tracing is controlled on a per "component" basis, where a "component" is a
 * name of the form aaa.bbb.ccc where aaa is the Most significant part; for
 * example, the utilities library might be called "utils", the doubly-linked
 * list "utils.dlist", and the code to destroy a list "utils.dlist.del"
 *
 */
class TraceImpl
{
    public:
        ...
}
```

Type alias declarations and typedefs should also be documented, although just a short summary is usually sufficient. Doxygen will automatically provide links to the types being renamed, if their documentation is available.



## Documenting Enumerated Types

An enumerated type is a type, and should be documented similarly to a class:

1. [Short Summary](#Short-Summary)
2. [Extended Summary](#Extended-Summary) (recommended)
3. [See Also](#See-Also) (optional)
4. [Notes](#Notes) (optional)
5. [References](#References) (optional)

In addition, each value of the type should be documented. A short description is almost always sufficient.

For example:

```
/**
 * Supported coordinate systems for flux-conserving transformations.
 *
 * These values are used in arguments to various functions in this package.
 * Unless otherwise stated, these functions do not validate whether the data
 * set makes sense in the "from" coordinates.
 */
enum class CoordType
{
    /// Untransformed detector coordinates.
    PIXEL,
    /// Idealized detector coordinates after applying a distortion correction.
    WARP_PIXEL,
    /// Equatorial J2000.0 coordinates.
    SKY_WCS
};
```



### Annotating Enum Values with Inline Comments (optional)

If the value descriptions are very short, you may choose to annotate values with inline comments after each constant, one per line. These comments are prefixed with `///<`.

For example:

```
enum class CoordType
{
    PIXEL,    ///< Untransformed detector coordinates
    WARP_PIXEL,    ///< Distortion-corrected detector coordinates
    SKY_WCS    ///< Equatorial J2000.0 coordinates
};
```

If the constant descriptions are too long to fit in a single line of source, ordinary documentation blocks before each constant must be used.



## Documenting Methods and Functions

All public or protected methods and all functions must be preceded by a documentation block. Method or function documentation blocks contain the following sections:

1. [Short Summary](#Short-Summary)
2. [Extended Summary](#Extended-Summary) (recommended)
3. [Template Parameters](#Template-Parameters) (if applicable; for classes, methods, and functions)
4. [Function/Method Parameters](#Function/Method Parameters) (if applicable; for methods and functions)
5. [Returns](#Returns) (if applicable; for methods and functions)
6. [Throws](#Throws) (if applicable; for methods and functions)
7. [Exception Safety](#Exception-Safety) (optional; for methods and functions)
8. [Helper Functions](#Helper-Functions) (if applicable; for functions)
9. [See Also](#See-Also) (optional)
10. [Notes](#Notes) (optional)
11. [References](#References) (optional)
12. [Examples](#Examples) (optional)

An example:

```
/**
 * Read an image from disk.
 *
 * \@param fileName the file to read. Must be either absolute or relative to
 *     the program working directory.
 *
 * \@return the image stored in `fileName`. If the image on disk does not
 *     have `double` pixels, they will be cast to `double`.
 *
 * \@throws IoError Thrown if `fileName` does not exist or is not readable.
 *
 * \@exceptsafe Strong exception guarantee.
 */
lsst::afw::image::Image<double> loadImage(std::string const & fileName);
```



### Overloaded Function/Method Definitions

`\@overload` may be used when two methods/functions are effectively the same but have different parameter lists for reasons of convenience. Use this tag **only** when the specification of the abbreviated overload can be easily inferred from the fully documented one.

The text generated by the `\@overload` tag tells readers to see the method “above”. Because Doxygen sorts the detailed documentation of namespace and class members, you should check the generated documentation to make sure the fully documented overload appears before any that use the `\@overload` tag.

For example:

```
/**
 * Sum numbers in a vector.
 *
 * \@param values Container whose values are summed.
 * \@return sum of `values`, or 0.0 if `values` is empty.
 *
 * \@exceptsafe This function does not throw exceptions.
 */
double add(std::vector<double> const & values);

/**
 * Sum numbers in an array.
 *
 * \@overload
 */
double add(double[] const values, size_t nValues);
```



## Documenting Constants, Variables, and Data Members

All non-private constants, variables, or data members must be preceded by a documentation block. At minimum, constants/variables/data members should have a summary line, but can also have a more complete structure:

1. [Short Summary](#Short-Summary)
2. [Extended Summary](#Extended-Summary) (recommended)
3. [Initializer Declaration](#Initializer-Declaration) (optional; for constants)
4. [Notes](#Notes) (optional)
5. [References](#References) (optional)
6. [Examples](#Examples) (optional)

For example:

```
/// Flag set if background subtraction should not be done.
const int NO_BACKGROUND = 1 << 3;
```



### Annotating Constants and Variables with Inline Comments (optional)

If the constant, variable, or data member descriptions are very short, you may choose to annotate them with inline comments after each value, one per line. These comments are prefixed with `///<`.

For example:

```
const int NO_BACKGROUND = 1 << 3;        ///< Skip background subtraction
```

If the descriptions are too long to fit in a single line of source, ordinary documentation blocks before each value must be used.





## Documentation References

> - [Stephen Colebourne's blog: Javadoc coding standards (joda.org)](https://blog.joda.org/2012/11/javadoc-coding-standards.html)
> - [Java - Documentation Comments (tutorialspoint.com)](https://www.tutorialspoint.com/java/java_documentation.htm)
> - [The Golden Rules of Code Documentation – Java, SQL and jOOQ.](https://blog.jooq.org/the-golden-rules-of-code-documentation/)
> - [Tips and Tricks for Better Java Documentation | JRebel & XRebel by Perforce](https://www.jrebel.com/blog/tips-and-tricks-for-better-java-documentation)
> - [Documenting C++ Code — LSST DM Developer Guide Current documentation](https://developer.lsst.io/cpp/api-docs.html#cpp-doxygen-parameters)

[Back to home](@ref mainpage)

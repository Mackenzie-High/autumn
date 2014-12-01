# Memoized Functions 

## Definition

A function that has the $AutumnLangAnnotationsMemoize$ annotation applied to it is a memoized-function.

## Purpose 

Memoized functions greatly simplify dynamic programming. 

# Behavior

A memoized function maintains a cache of previously computed values.
The cache maps lists of arguments to previously returned values. 
When the function is invoked, the cache is checked.
If the cache contains a mapping given the arguments passed to the function, then the previously return value is immediately returned. 
Otherwise, the function is executed and its return value is placed into the cache in case it is needed in the future.

## Details

+ Memoization is implemented using a $AutumnLangMemoizer$ object.
+ The size of the cache is unbounded by default; however, this can be changed.
+ Memoization can greatly speed up some algoritms.
+ One should be careful when using memoization, because it is a tradeoff between speed and memory. 

## Example

TODO
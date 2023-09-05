## Instance Field Resolution

## Static Field Resolution

+ Let T be the type that contains the sought after field.
+ Let N be the name of the sought after field. 
+ For each field F in T:
    + If the name of F is N:
        + Return F, because it is the sought after field. 

## Constructor Resolution

TODO: Finish this user-side documentation. 

## Instance Method Resolution

## Static Method Resolution 

+ Let T be the type that contains the sought after method. 
+ Let N be the name of the sought after method. 
+ Let A<sub>1</sub>, ... , A<sub>x</sub> be the types of the arguments being passed in the invocation.
+ Let U be an unsorted list of the methods in T.
+ Let S be the result of topologically sorting U.
+ For each method M in S do:
    + Determine whether the following conditions hold true:
        + M.name equals N
        + M.formals.length equals count(A)
        + A<sub>i</sub> is assignable to M.formals<sub>i</sub> ∀ i
    + If those conditions hold, return M, because it is the sought after method. 

## Sorting Methods and/or Constructors

+ Let X be a method or constructor. 
+ Let Y be a method or constructor. 
+ X is less than Y, iff:
    + X.name is lexicographically less than Y.name
    + X.formals.length is less than Y.formals.length
    + Y.owner is a subtype of Y.owner
    + ∃ <i>i</i> such that X.formals<sub>i</sub> is assignable to Y.formals<sub>i</sub> 
    + X.return-type is assignable to Y.return-type
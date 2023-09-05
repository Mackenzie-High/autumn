# Type Accessibility
A type T is accessible from within the definition of a type U, iff either:

+ T is U
+ T is in the same package as U
+ T is in a different package than U and T has public-access


# Type Member Accessibility
A member M of a type T is accessible in the definition of a type U, iff both:

+ T is accessible from U.
+ Either (M has public-access) or ((T and U are in the same package) and (M is not private)). 

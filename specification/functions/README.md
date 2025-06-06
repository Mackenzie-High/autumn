# Special Functions

+ [`all (condition : Predicate, elements : Iterable) : boolean`](14fa20047fd4abcdeb5310507514deec.md)
+ [`any (condition : Predicate, elements : Iterable) : boolean`](e5437531b521bc259f719e3eab639e79.md)
+ [`apply (functor : TypedFunctor, arguments : Iterable) : Object`](c408fd8994b218d70e22bd117d99db8f.md)
+ [`average (values : Iterable) : BigDecimal`](d3217aa546c45df5c92cc9f315e298e6.md)
+ [`big (value : Object) : BigDecimal`](360a4241d84ef12644ddd7b0b227c806.md)
+ [`compare (left : Comparable, right : Comparable) : int`](47b101bd06e0957b8ebc82d94598e7a7.md)
+ [`count (condition : Predicate, elements : Iterable) : int`](c1ebf92e8ea0a4e5a8063d45c1b0819e.md)
+ [`decodeJson (module : Module, string : String) : Object`](9777c6876663cd2374d217bab14f0495.md)
+ [`defaultValueOf (type : Class) : Object`](ec10a5aa7f6e096ab0b17dba6c454cfc.md)
+ [`encodeJson (object : Object) : String`](2315cb6c788c47fbdf0b703c4a634576.md)
+ [`enumerate (iterable : Iterable) : List`](5c35c192c33e97cbce64a1efeb36f6dd.md)
+ [`escape (string : String) : String`](838f6ac65abdb1dfc9bab51309f7a83c.md)
+ [`filter (condition : Predicate, elements : Iterable) : List`](8fcf80690af02555fed0e057b02e6e05.md)
+ [`find (condition : Predicate, skip : int, elements : Iterable) : Object`](bb79c6ca8f357cd5ac43636df53c9770.md)
+ [`findAnnotation (owner : AnnotatedElement, type : Class) : Annotation`](dba724ca6d72d6c47a5058cbc4a97dc6.md)
+ [`findConstructor (owner : Class, formals : Iterable) : Constructor`](373616027425db94901b148168b0b380.md)
+ [`findField (owner : Class, name : String) : Field`](df52cd68c7e58c80237e0c40c1bfaa10.md)
+ [`findMethod (owner : Class, name : String, formals : Iterable) : Method`](ecbc5767448790a66d60b74a40be3434.md)
+ [`first (list : List) : Object`](94e4d53b65bfd9e337ad731664c266e3.md)
+ [`format (format : String, args : Iterable) : String`](fc1b5a8c55ebf31205a07fa8701a2232.md)
+ [`get (anno : Annotation, index : int) : String`](8259f6bcc3ad902a22ac2be1fea37fc8.md)
+ [`get (array : BigDecimal[], index : int) : BigDecimal`](3838ea327d5b076c606ef0738699f5b5.md)
+ [`get (array : BigInteger[], index : int) : BigInteger`](75091f4981112d805fa193e3df067f62.md)
+ [`get (array : Object[], index : int) : Object`](d0743be481b6d84cad0ea30bbe4a7097.md)
+ [`get (array : String[], index : int) : String`](a6d98e83a1331bc9ce761d0167de9e04.md)
+ [`get (array : boolean[], index : int) : boolean`](cd38d3bb0d84d2b2a5f975cf5e32abd4.md)
+ [`get (array : byte[], index : int) : byte`](22cdce20a972d559a68b43503a76282a.md)
+ [`get (array : char[], index : int) : char`](a45744fa6e92f97d8d98075b159b7503.md)
+ [`get (array : double[], index : int) : double`](f61cd7e7a5a5e2534f6948ae504cc9a7.md)
+ [`get (array : float[], index : int) : float`](7d182cfacdfbd9cced56e20df19311e5.md)
+ [`get (array : int[], index : int) : int`](9f7980f9b4feaf3b34fc5a00c6f5c5d6.md)
+ [`get (array : long[], index : int) : long`](838a58ac4a4d387fa41fa23b3028ebd2.md)
+ [`get (array : short[], index : int) : short`](52b891b1198eb33ebba8a79e44b77f36.md)
+ [`get (list : List, index : int) : Object`](9bffddaf2549757245131be1e0cfa8fd.md)
+ [`get (map : Map, key : Object) : Object`](1ca8dd9ffdf67502b87336cde3e077a7.md)
+ [`get (record : Record, index : int) : Object`](c37f0065d7fcecbc3c2e66d92aea48de.md)
+ [`get (record : Record, key : String) : Object`](52a63f5e50c28cf19d3ba18fb34f1d45.md)
+ [`hash (value : Object) : int`](5c5dc97c8dfa77011f1143b43e871fd8.md)
+ [`immutable (original : Iterable) : List`](e794535f45812bd3e510b5b2112c2565.md)
+ [`immutable (original : List) : List`](b1a9ffdcfd5ed5a773bb9474294e0fa2.md)
+ [`immutable (original : Map) : Map`](dcb2ccc9e79a66f860665058d72e19a1.md)
+ [`immutable (original : Set) : Set`](157f8c82dc7aa66353893b12dcbd8724.md)
+ [`isAnnotationType (type : Class) : boolean`](c11c3990115bf8f3a65fb2c18bf17109.md)
+ [`isAssignableTo (assignee : Class, value : Class) : boolean`](2a40b86ed80320e5459f5f5a6606cca3.md)
+ [`isDesignType (type : Class) : boolean`](44ab449059063a2b7ded4279835570c4.md)
+ [`isEnumType (type : Class) : boolean`](4ea037adb72afd524a825e734a4e82b5.md)
+ [`isExceptionType (type : Class) : boolean`](2e2f201344d66e974dde170181db383a.md)
+ [`isFunctorType (type : Class) : boolean`](f0a67c2c727e54408865ed2ce528547d.md)
+ [`isModuleType (type : Class) : boolean`](a1c400dbd9601917f1fb668ac7073d69.md)
+ [`isRecordType (type : Class) : boolean`](82f19386f51f9d8ff0550a2dffc5c5b5.md)
+ [`isStructType (type : Class) : boolean`](26866beca248725171c11aa3f426303a.md)
+ [`isSubtypeOf (subtype : Class, supertype : Class) : boolean`](cea77713497761406e860449c017cfcf.md)
+ [`isTupleType (type : Class) : boolean`](1f320ded526fac6edaab0104ccdd1317.md)
+ [`iter (anno : Annotation) : List`](274fb7ce44c2234e0624aabef31d637e.md)
+ [`iter (input : Iterable) : List`](f0b3a1e4f7fa8ce4b664da26b7b16fe2.md)
+ [`iter (input : Iterator) : Iterable`](9a32d004dd9694abc3dc0f73d259c045.md)
+ [`iter (input : Map) : Set`](ff021319b298b3ef8fc8cd5f06f62672.md)
+ [`iter (input : Object[]) : List`](83a8b8faf0bbc2185d6e6a3818c87fd7.md)
+ [`iter (input : Record) : List`](ca16a6a8a58bde5d26bb28fee78ddd39.md)
+ [`iter (input : boolean[]) : List`](4064981bc3f28654f3b952de4a413521.md)
+ [`iter (input : byte[]) : List`](31762661a5a64560248d44639e59df3c.md)
+ [`iter (input : char[]) : List`](1b96da3235dcb963fb073153ec47436e.md)
+ [`iter (input : double[]) : List`](d88a6b45fdfde6c3a1db19b905e0a384.md)
+ [`iter (input : float[]) : List`](ef89d1ecb9b11305974676099aa9732a.md)
+ [`iter (input : int[]) : List`](03feefafe1f1c193fb405b0c36213d48.md)
+ [`iter (input : long[]) : List`](5f1279898ed3a9433ebedf7f7987f6c4.md)
+ [`iter (input : short[]) : List`](b93fd9a6b30a1bf557fe906e9c21a70a.md)
+ [`last (list : List) : Object`](af938a1b30e630e59b542b4c9d590398.md)
+ [`len (anno : Annotation) : int`](fa885ea662ee33e74d1b2dfe8aa7e3ea.md)
+ [`len (array : Object[]) : int`](7e1617bd629361badac3f77b56754c4b.md)
+ [`len (array : boolean[]) : int`](11d7878c489c5343ea938ebdfad0160d.md)
+ [`len (array : byte[]) : int`](99ff7d1d158ec1a8173456e62ba1a9ef.md)
+ [`len (array : char[]) : int`](f3cfe33eb7f9c03651d273765d97e08a.md)
+ [`len (array : double[]) : int`](53080c8388de507259240de8a969737c.md)
+ [`len (array : float[]) : int`](1ee28a10f3d1cd1e738c771f041a9cb6.md)
+ [`len (array : int[]) : int`](41a118aad30279b2c81d7f1f52595ef3.md)
+ [`len (array : long[]) : int`](4e325e8f416e65caf29cf42b5e85f65b.md)
+ [`len (array : short[]) : int`](3492c95666b5d9eb9b23f69028c97157.md)
+ [`len (collection : Collection) : int`](9539e85b0d09f0ddb7c2e1ca4de8517d.md)
+ [`len (map : Map) : int`](eda3cb4b11e3c72f4e344b685530bd82.md)
+ [`len (record : Record) : int`](5b0de7ebc269026f06c7feaca573c564.md)
+ [`len (string : CharSequence) : int`](64b7e219ac78ccd89e77766d477773cf.md)
+ [`map (functor : Function1, elements : Iterable) : List`](8c6c09416ad33d2fb9df8f94ac6f3d02.md)
+ [`maximum (values : Iterable) : Object`](787c27938d1aabb44f301418d959f0c5.md)
+ [`minimum (values : Iterable) : Object`](cd2848aaee2213c10d59fae0f8311f57.md)
+ [`mutable (original : Iterable) : List`](f66a83ad994b548da25f711e286b826e.md)
+ [`mutable (original : List) : List`](30933dca7cb23d5439cb7f5e48f58c99.md)
+ [`mutable (original : Map) : Map`](965df9d5b7aea583f19c09b735fac48c.md)
+ [`mutable (original : Set) : Set`](934b61b3765ec961b2d92d6ddf9b981c.md)
+ [`newArray (type : Class, size : int) : Object`](bbebcfb0c1d35047487b69f6fab0211d.md)
+ [`newProxy (type : Class, handler : ProxyHandler) : Object`](2f0e868635ed2bef7069279c9dc787b8.md)
+ [`padEnd (string : String, length : int, pad : char) : String`](c6eed97683ab9c707737723b6e5b9f04.md)
+ [`padStart (string : String, length : int, pad : char) : String`](42660e90e4db76293fb0a52d7a65e1d4.md)
+ [`parseBigDecimal (value : String) : BigDecimal`](057c96ac11eca6ee830307069f8d2104.md)
+ [`parseBigInteger (value : String) : BigInteger`](183c2cc97ef88918b2bf580e84c0a674.md)
+ [`parseBoolean (value : String) : boolean`](e28b0001982f1e3ce4733919145349ed.md)
+ [`parseByte (value : String) : byte`](4dfadc02927709a03e7f58a0f986dc9b.md)
+ [`parseDouble (value : String) : double`](b75341de8922a44a425a3faa29011a4b.md)
+ [`parseFloat (value : String) : float`](fc4b15ff724c2947f51e69db7071bd45.md)
+ [`parseInt (value : String) : int`](adb6b127c431ae2477d7ff9529f39d53.md)
+ [`parseLong (value : String) : long`](9504c04230476bd0ac229297f6272064.md)
+ [`parseShort (value : String) : short`](26a1d6ebc1caf41cab9130d3d6b0cac1.md)
+ [`print (value : Object) : void`](63cc28aae278ed40e9911cd7717ef07c.md)
+ [`printerr (value : Object) : void`](abc76797789a281a5aebff46746de705.md)
+ [`printerrf (format : String, args : Iterable) : void`](054f98c4c78c3ba57b79370cebfaf2e3.md)
+ [`printerrln () : void`](201c6a260d8c1bb6ce7e022e81c2b73d.md)
+ [`printerrln (value : Object) : void`](35003599815de5a57f074ff0bee530c6.md)
+ [`printerrlns (lines : Iterable) : void`](68d7bb63cf913ea3106cd3d30d693d38.md)
+ [`printf (format : String, args : Iterable) : void`](cb20dc58f4885f044fefcee1c5304594.md)
+ [`println () : void`](2348b8939d22ce4cf194bdcae5aaf43f.md)
+ [`println (value : Object) : void`](5dd685065dcfc5fd97c5720cefa7af2c.md)
+ [`printlns (lines : Iterable) : void`](4b7427952bee7c96e139857252693ba4.md)
+ [`raise (exception : Throwable) : void`](ded5e8f1184a5e58bb2d993ab5d0ab36.md)
+ [`range (minimum : int, maximum : int) : Iterable`](27a85104d8c545661b6951e5fd33ec60.md)
+ [`range (minimum : int, maximum : int, step : int) : Iterable`](c87f72d1d5edd3c7019c75cbb2b85bca.md)
+ [`readln () : String`](3a121a3415d152c3e5361e2580f34a34.md)
+ [`recall (target : Object, key : Object) : Object`](29a2f5cfa5cf4e1974554c44eb0dc62a.md)
+ [`remember (target : Object, key : Object, value : Object) : void`](7a266d4d9fa6ad4ab819e0dd919126ea.md)
+ [`reset (record : Record) : Record`](8b5e3cc91c4d7c3b47eea2b2edebfdc8.md)
+ [`rethrow (exception : Throwable) : void`](b5c28241a64373f192f3fa80cb24bbdf.md)
+ [`set (array : BigDecimal[], index : int, value : BigDecimal) : BigDecimal[]`](c77389c7eb79437c2d2ad31ed073934f.md)
+ [`set (array : BigInteger[], index : int, value : BigInteger) : BigInteger[]`](f2da3cb8e914737cba6f02232d14726a.md)
+ [`set (array : Object[], index : int, value : Object) : Object[]`](eb028a172fe6550ae1982a52a116ffd0.md)
+ [`set (array : String[], index : int, value : String) : String[]`](9d1ebd4f12019ed2ea778bbab88e8d16.md)
+ [`set (array : boolean[], index : int, value : boolean) : boolean[]`](7ff91d38e11886439f0421a7459167c1.md)
+ [`set (array : byte[], index : int, value : byte) : byte[]`](a4eb263f22e75ab6e687abb2c8520f89.md)
+ [`set (array : char[], index : int, value : char) : char[]`](59cc36f96504288a5682b56d7eee9b24.md)
+ [`set (array : double[], index : int, value : double) : double[]`](31dac222de258ea569e7643ce9a2f336.md)
+ [`set (array : float[], index : int, value : float) : float[]`](02d94d2b2488a3e5703dad13091dcd95.md)
+ [`set (array : int[], index : int, value : int) : int[]`](15220ead9f009d824188c9c034f2fde0.md)
+ [`set (array : long[], index : int, value : long) : long[]`](77c23891c7ffeceb888123613d9630e3.md)
+ [`set (array : short[], index : int, value : short) : short[]`](e98c11d313122f98f26f0fea8977419e.md)
+ [`set (assignee : Record, value : Record) : Record`](e5ca1b17184e0ad0b44e8ef0628eaf04.md)
+ [`set (assignee : Record, values : Map) : Record`](7bc3587d3c2aada966afd35027ca8a9b.md)
+ [`set (list : List, index : int, value : Object) : List`](2f5fc3e5eeae32a36adb17f18e93c990.md)
+ [`set (map : Map, key : Object, value : Object) : Map`](9994fb69d9b90a49d454838442cff7c4.md)
+ [`set (owner : Record, index : int, value : Object) : Record`](7b2fda83c1b3bdb3989f53a3af5e7d32.md)
+ [`set (owner : Record, name : String, value : Object) : Record`](a204b3c1d4e8a58cfb2476b7a686b98f.md)
+ [`str (iterable : Iterable, prefix : String, separator : String, suffix : String) : String`](01a51c790e1608d2c3b764820b176837.md)
+ [`str (value : Object) : String`](e5f7232e762e454bb9a6018d7a39a452.md)
+ [`sum (values : Iterable) : BigDecimal`](ebd55fe2c09aa278a7f151d65ac1b3cb.md)
+ [`sync (locked : Object, action : Action) : void`](e88e95508c56cea09b8303929a4aca6e.md)
+ [`unescape (string : String) : String`](b6dd5a759bc56179865723ada2f49e6d.md)
+ [`unique () : BigInteger`](2e2365b5a9c5a36bed943871c3a0ec3f.md)
+ [`unmodifiable (original : List) : List`](15237e8c5430de70c534258d464fb648.md)
+ [`unmodifiable (original : Map) : Map`](811a8a0cf9bbcbb1fdde0645f9aba7aa.md)
+ [`unmodifiable (original : Set) : Set`](c1361f25d75ba56dabbe26623452424a.md)
+ [`zfill (string : String, length : int) : String`](42928091e08bb245238470736f879c43.md)
+ [`zip (iterables : Iterable) : List`](f05bbe005fefce813c816ef66736f8de.md)


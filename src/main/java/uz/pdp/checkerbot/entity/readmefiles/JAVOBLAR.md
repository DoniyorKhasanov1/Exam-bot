## 1. Thread nima?
  Thread - bu bir process (jarayon) ichidagi mustaqil ish bajaruvchi eng kichik birlik.
  Thread'lar bir process ichida mustaqil vazifalar (tasks) bajaradi, lekin resurslardan (xotira, data) umumiy foydalanadi.
***
## 2. Multithreading va Multitasking farqi nimada?
  Multitasking - bu bir kompyuter yoki odamning bir vaqtning o'zida bir nechta to'liq jarayonni bajarishi hisoblanadi. 
  Multitasking'ni alohida bir process deb tasavvur qilsak bo'ladi.
  Masalan bir inson bir vaqtning o'zida musiqa eshitish, kod yozish yoki internetda browsing qilish kabi amallarni bir vaqtda bajarishidir.
  Kompyuter kabi mashinalarda esa bu jarayonga o'yin o'ynash, Browser oynasidagi jarayonlarni boshqarish kabi amallarni 
  misol qilib keltirish mumkin. 
  Multithreading - bu bitta protses ichida bir nechta oqimlar (threads) turli xil vazifalarni mustaqil bajarishidir. 
  Multithreading da thread lar alohida vazifalarni bajarsa ham, kompyuter resurslaridan umumiy foydalanadi.
***
## 3. ThreadPool nima?

   Thread Pool - bu dasturimizda oldindan yaratilgan thread'lar to'plamidir. Bu class `java.util.concurrent` paketida joylashgan. 
   Java 1.5 versiyadan boshlab taqdim etiladi. Multithreaded dasturlarda, odatda ko'plab 
   threadlar bir vaqtda ishlaydi ishini tugatgach, GC tomonidan o'chirib yuboriladi. Dasturda har safar yangi thread yaratilsa
   ortiqcha xotira resurslaridan foydalanilishiga va hattoki Memory leak ga ham olib kelishi mumkin. Shu muammoni hal qilishda
   ThreadPool juda qo'l keladi. Biz `java.util.concurrent` paketidagi `ExecutorService` interfeysi va `Executors` class i orqali
   aniq bir o'lchamga ega ThreadPool (Threadlar to'plami) yaratishimiz mumkin. Bu usul orqali biz dasturda har safar yangi 
   thread yaratmasdan ThreadPool dagi avvaldan yaratilgan threadlardan foydalanib threadlardan yanada unumli foydalanishimiz mumkin.
***
## 4. Callable vs Runnable
  `Runnable` va `Callable<V>` interfeyslari orasidagi farq, biz `Runnable` interface orqali thread yaratganimizda undagi `run()`
  metodi orqali thread ni ishga tushirishimiz mumkin. `Runnable` thread lar task ni bajaradi va hech qanday natija qaytarmasdan
  threadlar `DEAD` holatiga o'tadi, yani o'chib ketadi. `Callable<V>` interface `call()` metodini taqdim etadi. Bu metod orqali
  biz thread yaratishimiz va ishga tushirishimiz mumkin, lekin `Runnable` dan farqi bu metod bizga thread dagi natijani olish
  imkonini beradi va biz bu natija ustida boshqa amallarni ham bajarishimiz mumkin.
***
## 5. `Future` nima va qachon ishlatilinadi?
   `Future<V>` interface - bu threadlardan qaytgan natijani olish uchun ishlatiladigan interfeys bo'lib, thread'dan qaytgan natijani
   `get()` metodi orqali olish va keyinchalik natija ustida boshqa amallar qilish imkoniyatini beradi
***
## 6. `Future<V>` va `CompletableFuture<V>` o'rtasidagi farq
  `Future<V>` interfeysi orqali biz faqat threadning natijasini olishimiz mumkin, `CompletableFuture<V>` da esa natijani olib,
  interfeys taqdim etadigan `thenApply()`, `thenAsync()` kabi metodlar orqali natija ustida boshqa amallarni ham asinxron 
  tarzda amalga oshirishimiz mumkin bo'ladi.
***
## 7. `volatile` non-access modifier haqida
   Java da multithreaded dasturlar bilan ishlaganimizda bazi fieldlarning malumotlaridan barcha threadlar umumiy foydalanishi, o'qishi
   kerak bo'lishi mumkin. 
   ```java
    public volatile Integer count;
   ```
 Biz fieldlarga yuqoridagidek qilib `volatile` keyword berishimiz orqali uning qiymatini main memory'da qilib belgilaymiz.
 Ya'ni bu field dan foydalangan thread uning qiymatini o'zgartirsa, o'zgargan qiymat undan foydalaniladigan boshqa threadlarda
 ko'rinadi, bu xuddiki biz biror field ni `static` qilib belgilaganimizdek.
***
## 8. Immutable classlar nima uchun kerak va immutable class yaratishimiz uchun qanday shartlar bajarilishi kerak?
   Immutable class lar yaratish bizga bazi malumotlar dastur ishlash davomida o'zgarmasligi kerak bo'ladi. Immutable class
   yaratishimiz uchun birinchi navbatda class ni `final` keywordi bilan belgilashimiz kerak. Bu uning boshqa class lar tominidan
   `extends` olinmasligini ta'minlaydi. Class malumotlari `private` bo'lishi kerak, chunki boshqa class lar tomonidan uning
  field lariga kirish oldini olish kerak. Keyingi navbatda, biz bu class malumotlaridan foydalanmoqchi bo'lsak, getter/setter lar
  yaratishimiz va bu metodlarda uning deep copy sini berib yuborishimiz kerak bo'ladi.
***
## 9. Asynchronous programming nima?
   Asynchronous programming - bu bir dastur bajarilishida thread lar bir birini kutmasdan, resurslardan alohida (thread cachelardan foydalanib)
   ishlatilish paradigmasiga aytiladi. Bu paradigm da har bir thread da cache bo'ladi va thread da ishlatilgan field lar malumotlari
   shu cache larda saqlanadi. Bu usul orqali dastur ishlash samaradorligi ortadi.
*** 
## 10. Atomic class lar qanday algoritm orqali race condition oldini oladi?
   Could not response this question!
***
## 11.Serialization va deserialization nima?
   Serialization - bu malumotni uzatishda yoki biror faylga saqlashda inson tushunmaydigan belgilarga aylantirish. 
   Deserialization - bu serialized malumotlarni encryption holatidan inson tushunadigan holatga o'tkazishga aytiladi
***
## 12. Serializable vs Externalizable interfaces
***
## 13. ReentrantLock haqida malumot
  `ReentrantLock` - bu biror thread ishlashi davomida thread foydalanayotgan malumotni sinxron qilish uchun ishlatiladigan class 
   hisoblanadi. Biz biror thread ishni boshlaganida `ReentrantLock` obyektidan `lock()` metodini ishlatib threadni synchronized holatiga o'tkazishimiz
  mumkin. Thread tugaganida esa uni yana `unlock()` qilishimiz mumkin. Bu asosan thread larda race condition oldini olish va data integrity ni taminlashda ishlatiladi
***
## 14. Logging Nima?
  Logging - bu dasturimiz ishlash jarayonida yuz beradigan xatoliklar, ma'lumotlar va dastur oqimi (execution flow) holatini
  malum bir faylga yozib bayon qilib ketishga aytiladi. Java da `java.util.logging` paketidagi Logger class dan foydalanib
  malumotlarni log qilib borishimiz mumkin. Masalan:

  ```java
  import java.util.logging.Logger;

Logger logger = Logger.getLogger("name");
  ```
Logger classida log larning turli xil darajalari mavjud: `WARNING`,`INFO`.
***
 ## 15. Daemon thread nima?
   Multithreading da ikki xil thread mavjud: User thread va Daemon thread. User thread lar biror task ni mustaqil ravishda bajara olishadi, yani 
   qaysidir thread ishini tugatib o'lsa, User thread ishini oxirigacha davom ettirish imkoniyatiga ega. Daemon thread lar 
   esa malum bir thread ga bog'liq ishlaydi, yani biror User thread ga. Malum bir User thread o'lsa, unga bog'langan daemon 
   thread ham vazifasini tugatmaganiga qaramasdan o'chib ketadi.
***
## 16. Jar file nima?
   JAr (Java ARchive) file - bu dasturchi tomonidan yaratilgan maxsus kutuxonalarning .jar ko'rinishidagi yig'indisi hisoblanadi.
   Jar file lar distributed systems da koproq ishlatilinadi, chunki bu fayllarni JVM ga ega bo'lgan boshqa OS lar ham tayyor kodlaridan 
   foydalanish imkoniyatiga ega bo'ladi.
***
## 17. Maven nima?
  Maven - bu loyihalarni qurishda project management control kabi ishlarni avtomatlashtiradigan built-tool hisoblanadi.
  

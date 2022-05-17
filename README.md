** Projenin çalıştırılabilmesi için Mongo db data source ayarları application.properties içerisinde yapılmalıdır.

** Proje 2 tip user barındırır (ADMIN , CUSTOMER ) . Proje ilk çalıştığında bir Admini otomatik tanımlar (email = admin@readingisgood.com , password = 123456 ) 

** Sisteme Customer kayıtlarında herhangi bir yetki gerekmez fakat Admin tanımını başka bir admin useri ile yapmak gerekmektedir.

** Token kullanımı şu şekilde yapılmalıdır (Bearer JWT_KEY )

** Swagger'a proje_path/swagger-ui.html üzerinden erişebilirsiniz. (http://localhost:8080/swagger-ui.html) 


  ***** Senaryo Dışı Eklentiler ****
  
    ** Senaryoya iki kullanıcı aynı anda stoktaki son ürünü almaya çalışırsa engellenmesi için Sepet özlliği ekledim. Şipariş vemeden önce kullanıcı ürünü sepete eklemeli daha sonrasında order controller kullanılarak sepet onaylanmalı.
    ** Müşetirlerin sepeti onaylıyabilmesi için userAdressController kullanılarak sisteme adres kaydı gereçekleştirmesi gerekmekte.
    ** Sepetteki ürünler 15 dakika içerisinde satın alınmaz ise sistem sepetten kaldırır.(@Schedule kulanıldı)


  *****Admin yetkisindeki end pointler****
  
    - /authentication/new-admin-account
    - /book/add
    - /book/change-stock
    - /statistic/admin/system
    - /statistic/admin/user/{userId}
    - /system-log/get

  *****Customer yetkisindeki end pointler****
  
    - /order/**
    - /shopping-cart/**
    - /statistic
    - /user-address/**

  *****Açık end pointler****
  
    - /authentication
    - /authentication/new-account
    - /book/get
  
  

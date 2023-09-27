# Minesweeper

- Oyun metin tabanlıdır. 
- Oyun çift boyutlu diziler üzerinden oynanmakta olup ve MineSweeper sınıfı içerisinde tasarlanmıştır. 
- Matris boyutunu (satır ve sütun sayısını) kullanıcı belirlemektedir. 
- Diziye ait eleman sayısının çeyreği (elemanSayisi / 4) kadar rastgele mayın yerleştirilmektedir. Örneğin matris 4x3 boyutunda ise eleman sayısı (satırSayısı * sütunSayısı) formülü ile hesaplanmakta ve boyutu 12 olmaktadır. Bu durumda mayın sayısı 12 / 4 = 3 adet olarak belirlenmektedir.
- Kullanıcı matris üzerinden bir nokta seçmekte olup, seçtiği noktayı için satır ve sütun değerleri olarak girmektedir. 
- Seçilen noktanın dizinin sınırları içerisinde olup olmadığı kontrol edilip, koşul sağlanmaması durumunda tekrar nokta istenmektedir. 
- Kullanıcı, seçtiği koordinatta mayın olması durumunda oyunu kaybetmektedir. 
- Seçtiği koordinatta mayın yok ise, ilgili noktaya değen tüm konumlara bakılmaktadır (sağı, solu, yukarısı, aşağısı, sol üst çapraz, sağ üst çapraz, sağ alt çapraz, sol alt çapraz) ve etrafındaki mayınların sayısının toplamı ilgili noktaya yazılmaktadır. Noktaya değen herhangi bir mayın yok ise "0" değeri atanmaktadır. 
- Kullanıcı hiç bir mayına basmadan tüm noktaları seçebilirse oyunu kazanmaktadır.
- Oyunun sonlanması durumunda çözüm haritası kullanıcıya gösterilerek kendisine kazanma/kaybetme durumu uygun mesaj ile terminale yazdırılmaktadır. 

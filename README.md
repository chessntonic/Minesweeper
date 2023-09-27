# Minesweeper

Java dilinde Mayın Tarlası oyunu geliştirdik.

Oyun Kuralları :
- Oyun metin tabanlıdır.
- Çift boyutlu diziler üzerinden oynanan oyun MineSweeper sınıfı içerisinde tasarlandı.
- Matris boyutunu (satır ve sütun sayısını) kullanıcı belirliyor.
- Diziye ait eleman sayısının çeyreği (elemanSayisi / 4) kadar rastgele mayın yerleştiriliyor. Örneğin dizi 4x3 boyutunda ise eleman sayısı (satırSayısı * sütunSayısı) formülü ile hesaplanıyor.
  Boyut 12 olduğundan 12 / 4 = 3 adet mayın oluşuyor.
- Kullanıcı matris üzerinden bir koordinatı satır ve sütun değerleri girerek seçiyor.
- Seçilen noktanın dizinin sınırları içerisinde olup olmadığını kontrol ediliyor ve koşul sağlanmazsa tekrar nokta isteniyor.
- Kullanıcının girdiği noktada mayın var ise kullanıcı oyunu kaybediyor.
- Mayın yok ise, ilgili noktaya değen tüm konumlara bakılıyor (sağı, solu, yukarısı, aşağısı, sol üst çapraz, sağ üst çapraz, sağ alt çapraz, sol alt çapraz) ve etrafındaki mayınların  
  sayısının toplamı ilgili noktaya yazılıyor. Noktaya değen herhangi bir mayın yok ise "0" değeri atanıyor.
- Kullanıcı hiç bir mayına basmadan tüm noktaları seçebilirse oyunu kazanıyor.

# OCR

Projekt dotyczy analizy dostępnych na rynku narzędzi do przeprowadzania optycznego rozpoznawania znaków (OCR), przeprowadzeniu testów porównawczych oraz wytypowaniu najlepszego narzędzia.

|    Nazwa   |System Operacyjny| liczba języków | polski | technologie    | licencja    | wsparcie        |                      link do repo                     |
| :---:   | :-: | :-: | :-:| :---:   | :-: | :-: |:-:|
|  Tesseract-OCR | Unix            |      ~200      |  tak   | java, python, php, .NET,  | Apache 2.0  | na bieżąco      | https://github.com/tesseract-ocr/tesseract   |
|  EasyOCR   | Windows         |      ~80      |  tak   | python  | Apache 2.0  | na bieżąco      | https://github.com/JaidedAI/EasyOCR                  |
|  Calamari  | Unix/Windows    |       ~100   |  tak   | python, w javie jako proces | Apache 2.0  | na bieżąco      | https://github.com/Calamari-OCR/calamari             |
|  Kraken    | Unix            |        ~100       |  tak   | python, w javie jako proces | Apache 2.0  | na bieżąco      | https://github.com/mittagessen/kraken      |
|  OCRopus   | Unix            |  ~100  |  tak   | python, w javie jako proces  | Apache 2.0  | w ubiegłym roku | https://github.com/ocropus/ocropy                    |
|  GOCR      | Unix            |       ~20      |  nie   | GO, w javie jako proces | GNU GPL v2 | 5 lat temu      | https://github.com/eaciit/gocr                       |
|  OCRAD     | Unix            |       ~15      |  nie   | w javie jako proces | GNU GPL v2 | 5 lat temu      | https://github.com/kba/ocrad-docker                  |
|  Cuneiform | Unix            |       ~28      |  tak   | C/C++, w javie jako proces | GNU GPL v2 | 13 lat temu     | https://github.com/jwilk-mirrors/cuneiform-multilang |
|  gscan2pdf | Unix            |        ~30     |  tak   | Pearl, w javie jako proces | GNU GPL | 3 lata temu     |https://github.com/marschap/gscan2pdf                |

Graphical software:

|    Nazwa     |System Operacyjny| liczba języków | polski | licencja    | wsparcie        |                    link do repo                       |
| :---:   | :-: | :-: | :-:| :---:   | :-: | :-:|
|  Paperwork   | Unix            |      ~150      |  tak   | GNU GPL v3 | na bieżąco      |  https://gitlab.gnome.org/World/OpenPaperwork/paperwork |
|  Lios        | Unix            |      ~200      |  tak   | GNU GPL v3 | na bieżąco      | https://github.com/Nalin-x-Linux/lios-3                |
|  OcrFeeder   | Unix            |      ~50       |  tak   | GNU GPL v3 | na bieżąco      | https://github.com/GNOME/ocrfeeder                     |
| Adobe Acrobat| Windows         |                |  tak   | Adobe      | na bieżąco      |  -                                                      |
|  gImageReader| Unix            |      ~200      |  tak   | Apache 2.0 | na bieżąco      |   https://github.com/manisandro/gImageReader             |

Cloudowe:

|    Nazwa               |System Operacyjny| polski | wsparcie    |
| :---:   | :-: | :-: | :-:|
|  Abby Cloud            | Windows         |  tak   |  na bieżąco |
|  Google Cloud Vision   | Unix/Win        |  tak   |  na bieżąco |
|  Microsoft Azure Vison | Windows         |  tak   |  na bieżąco |


#### Opis wybranych narzędzi: <br>

**Tesseract**- narzędzie służące do przeprowadzania OCR działające na różnych systemach operacyjnych. Jest darmowym oprogramowaniem wydanym na licencji Apache 2.0. Uważany za najbardziej dokładny open sourcowy program do OCR. 

**OCRopus** – działa na systemach unixowych. Darmowe oprogramowanie wydane na licencji Apache 2.0. Przeprowadza najpierw binaryzację obrazu, dzieli na linie następnie wydobywa tekst. <br>

**Ocrad** – wydany na licencji GNU GPL. Wczytuje obrazy w formacie pixmap i przetwarza na tekst. Umożliwia podział na kolumny, bloki. <br>

**GOCR** - oprogramowanie służące do rozpoznania tekstu, wydane na licencji GNU GPL v2.


Wykorzystanie w językach programowych (główne zainteresowanie Java) <br>
Jako jedyny w zestawieniu Tesseract posiada biblioteki umożliwiające wykorzystanie go w większości języków programowania. Żeby wywołać pozostałe narzędzia należy uruchomić je jako procesy.

Tesseract-OCR dostępny jest również jako projekt w dockerze z sytemem kolejkowym: https://github.com/tleyden/open-ocr

### Wyniki testów porównawczych:
Testy przeprowadzono na 100 plikach w formacie .png, o rozmiarach z zakresu od ~50KB do ~750KB <br>
Tesseract-OCR (API) uzyskał średnie czasy w okolicach 2s. <br>
Tesseract-OCR (jako proces) uzyskał średnie czasy na poziomie 1,2-1,3s <br>
GOCR (jako proces) uzyskał średnie czasy na poziomie 0,5s <br>

Dokładność przeprowadzonej analizy jest większa dla Tesseract-OCR. (Uzyskane teksty są bliższe tym orginalnym)

### Wnioski:
Wnioski po przeprowadzeniu analizy oraz testów porównawczych (Tesseract-ocr a GOCR): <br>
- Tesseract - średni czas pracy jest większy niż czas pracy GOCR
- Uzyskane wyniki przez Tesseract są dokładniejsze
- Tesseract jako jedyne narzędzie posiada biblioteki umożliwiające wykorzystanie go w różnych językach programowania.
- Najbardziej powszechnie używanym narzędziem jest Tesseract
- Posiada wsparcie dla języka polskiego
- W przedstawianych poniżej rankingach Tesseract znajmuje topowe pozycje.

Z powyższych wniosków wynika, że najlepszym w zadanych kryteriach dostępnym narzędziem jest Tesseract-ocr.

### Rankingi
Znalezione rankingi przedstawianych narzędzi: <br>
Zestawienie pod względem dokładności: https://www.slant.co/topics/2579/~best-ocr-libraries <br>
Zestawienie najlepszych narzędzi dostępnych na linuxie wraz z porównaniem przedstawianych: https://www.linuxlinks.com/ocrtools/ <br>
Zestawienie narzędzi na linuxie: https://linuxhint.com/ocr-apps-linux/ <br>
Zestawienie dostępnych narzędzi: https://source.opennews.org/articles/so-many-ocr-options/ <br>

### użyteczne linki: <br>
Lista zawierająca opis narzędzi i bibliotek: https://github.com/kba/awesome-ocr <br>
Repozytorium zawierające testy porównawcze narzędzi: https://github.com/factful/ocr_testing <br>

# Jak uruchomić aplikację:
Argumenty programu, które należy podać:<br>
1. wybrane narzędzie (tesseractAPI/tesseract/gocr)
2. wybrany język (pol w przypadku polskiego języka)
3. Ścieżkę do plików, które mają zostać poddane działanią programu.
4. Ścieżkę do miejsca docelowego, gdzie mają zostać zapisane pliki wynikowe programu (w przypadku nie podania parametru przyjmowana jest ścieżka parametru 3)

Należy mieć zainstalowane Tesseract-OCR oraz GOCR
Instalacja Tesseract-OCR (przykład dla Ubuntu 20.04): <br>
Deafaultowa instalacja z językiem angielskim:<br>
sudo apt-get install tesseract-ocr

dodanie języka polskiego: <br>
sudo apt-get install tesseract-ocr-pol
lub dodanie wszystkich dostępnych języków: <br>
sudo apt-get install tesseract-ocr-all

Instalacja GOCR:<br>
sudo apt-get update -y
sudo apt-get install -y gocr

Preferowany format pliktów to: .png, .jpg, .tiff

* Tentang Choose Ur Trashcan

Choose Ur Trascan [0] adalah permainan berbasis android yang dikembangkan untuk 
memupuk sikap peduli lingkungan bagi pengguna dan masyarakat luas. Fokus cakupan 
adalah permainan berbasis android single touch yang dapat diaplikasikan di semua 
ponsel pintar berbasis android.

Penyajian aplikasi pada permainan ini diusahakan seakurat mungkin terhadap data 

* Tentang repositori Choose Ur Trashcan

Repositori ini berisi seluruh data yang ada di Choose Ur Trashcan

* Bagaimana menggunakan data ini? (a.k.a.: Panduan bagi pengguna)

Mayoritas data (terletak di direktori ~table/~) disimpan dalam format CSV dan
dapat dengan mudah dimuat ke program spreadsheet, diimpor ke database
relasional, diparse oleh skrip, dll. Akan disediakan juga skrip (di ~bin/~)
untuk mengimpor ke database SQL, mengupdate data di database SQL, dll.

Silakan baca dokumen ini secara keseluruhan. Jika Anda masih memiliki pertanyaan
mengenai cara pemakaian, dapat menghubungi [4]. Silakan lihat juga effort serupa
seperti [3].

* Panduan bagi kontributor

1. Baca dokumen ini hingga selesai.
2. Mendaftar ke github [6] jika belum.
3. Fork proyek ini [7].
4. Kirimkan patch (pull request) atau laporkan isu lewat github.
5. Jika Anda berminat ikut menjadi editor, silakan hubungi [4]. Tugas editor
   adalah mengecek akurasi data/perubahan data.

* Panduan bagi pengembang aplikasi

Untuk saat ini, silakan lihat [[Panduan bagi pengguna]].

* Pengorganisasian data

Mayoritas data ada dalam bentuk tabel 2 dimensi. Data tabel ditaruh di direktori
~table/~. Di bawah direktori ~table/~, setiap tabel ditaruh di subdirektori
masing-masing. Data tabel umumnya disimpan dalam file ~data.csv~ karena format
CSV cukup universal dan memudahkan impor ke aplikasi spreadsheet atau yang
lainnya. Terdapat ~readme.txt~ di setiap subdirektori tabel yang memperkenalkan
sekilas tentang isi tabel, sejarah, isu, dll. Enkoding data adalah UTF-8,
kecuali disebutkan lainnya di file ~readme.txt~. Metadata disimpan dalam file
~meta.yaml~ (atau ~meta.json~).

* Struktur metadata

Metadata ditulis dalam bahasa schema Sah [8] untuk memudahkan validasi (namun
mohon maklum, saat ini modul Perl untuk parser Sah belum selesai dikembangkan).

** Metadata untuk tabel

Schema untuk metadata tabel mengikuti table_spec yang didefinisikan oleh modul
Perl Perinci::Sub::Gen::AccessTable [9]. Untuk contohnya, lihat ~meta.yaml~ pada
salah satu tabel yang memilikinya.

** Metadata untuk kolom

Schema untuk metadata kolom belum ditulis, untuk saat ini silakan mengintip
dokumentasi modul Perl Perinci::Sub::Gen::AccessTable [9]. Untuk contohnya,
lihat ~meta.yaml~ pada salah satu tabel yang memilikinya.

* Panduan gaya

** Penamaan file, tabel, kolom

1. Nama file, tabel, kolom menggunakan huruf kecil. Alasan: agar tidak
   bermasalah dengan filesystem yang case-insensitive, kita menghindari
   permainan huruf besar/kecil.

   Ini juga termasuk akronim, contoh ~html~ bukan ~HTML~, ~css~ bukan ~CSS~,
   ~js~ atau ~javascript~ bukan ~JavaScript~. Ini mencegah kebingungan singkatan
   yang kadang memiliki varian huruf besar/kecil (contoh: ~perl~ dan ~Perl~ dan
   ~PERL~).

2. Hanya gunakan huruf di karakter pertama dan huruf/garis bawah (~_~)/angka di
   karakter selanjutnya. Alasan: ini paling aman karena membuat nama bisa
   langsung dipakai sebagai nama kolom di database, nama variabel di bahasa
   pemrograman, dll. Catatan: metadata memuat juga nama alias bahasa Indonesia.

3. Gunakan kata penuh dan bukan singkatan (contoh: ~province~ bukan ~prov~, ~),
   kecuali jika singkatan sudah lazim/sering dipakai dan relatif tidak ambigu.
   Contoh yang bisa disingkat: ~ind~ untuk bahasa dan wilayah Indonesia, ~eng~
   untuk english (keduanya amat sering dipakai). ~org~ untuk organization (juga
   sering, amat mempersingkat). ~str~ untuk string, ~len~ untuk length, dll.

   Jika ragu mengenai penyingkatan, hubungi editor [4].

   Daftar lainnya:

     - min untuk minimum, minimal
     - max untuk maximum, maximal

4. Jika nama terdiri dari dua atau lebih kata, pisahkan tiap kata dengan garis
   bawah (~_~), contoh: ~paper_size~. Jangan menyatukan kata (~papersize~) atau
   menggunakan camel case (~paperSize~). Alasan: pemisahan dengan ~_~ membuat
   kata majemuk lebih mudah dibaca terutama untuk pembaca nonnatif.

   Ini juga termasuk kata-kata yang disingkat (contoh: ~str_len~ dan bukan
   ~strlen~), kecuali beberapa perkecualian yang amat jarang. Alasan: lebih
   konsisten.

   Perkecualian: nama skrip (di direktori ~bin/~) menggunakan setrip ~-~ sebagai
   pemisahnya. Ini hanya masalah gaya saja, saya merasakan ~-~ lebih umum
   digunakan untuk skrip dan lebih mudah diketik.

5. Berikan prefiks ~idn_~ (kode negara ISO 3-huruf untuk Indonesia) untuk entiti
   yang berisi data spesifik untuk bahasa/wilayah Indonesia (contoh: tabel
   ~idn_province~ berisi daftar provinsi di Indonesia, sementara ~province~ saja
   mencerminkan tabel berisi daftar provinsi di seluruh dunia

   Berikan prefiks ~ind_~ (kode bahasa ISO 3-huruf untuk bahasa Indonesia) untuk
   kolom yang berisi teks bahasa Indonesia. Contoh: ~ind_name~ menyatakan kolom
   berisi nama Indonesia. Sebaliknya, gunakan prefiks kolom ~eng_~ untuk data
   yang berisi bahasa Inggris, walaupun ini tidak wajib dan umumnya hanya perlu
   jika ada kolom Indonesia-nya.

   Pada nama alias bahasa Indonesia, akhiran ~_idn~, ~_ind~, dan ~_eng~ dapat
   digunakan sebagai padanannya.

6. Nama file: selalu gunakan akhiran (ekstensi) file yang mencerminkan isi file.
   Contoh: ~changes.txt~ dan bukan ~changes~ saja. Alasan: Ini membantu skimming
   dan juga membantu saat bekerja di sistem operasi yang mengandalkan ekstensi
   file (seperti Windows). Perkecualian: dokumen berformat Org disimpan ke file
   berakhiran ~.txt~.

7. Gunakan bahasa Inggris (contoh: ~name~ bukan ~nama~). Ejaan American english
   lebih disukai daripada British (contoh: ~color~ bukan ~colour~, ~center~
   bukan ~centre~, ~organization~ bukan ~organisation~). Alasan: Di bahasa
   pemrograman bahasa Inggris (terutama dialek American) sangat dominan, tidak
   perduli apakah program tersebut dikembangkan oleh orang Indonesia atau luar.
   Ini memudahkan nama yang kita pilih dipakai langsung sebagai nama variabel,
   modul, dsb di bahasa pemrograman.

   Penggunaan bahasa Inggris juga akan memudahkan pertukaran data dengan
   Wikipedia bahasa Inggris misalnya.

8. Gunakan bentuk kata tunggal (singular) dan bukan jamak (plural). Alasan:
   lebih sederhana dan menghindari aturan penjamakan bahasa Inggris (mis: apple
   -> apples tapi leaf -> leaves, sheep -> sheep, dsb). Perkecualian: kolom yang
   selalu berisi array/list, contoh ~eng_tags~.

9. Untuk tabel yang berisi nama (mis: tabel ~country~ berisi data nama negara),
   usahakan selalu memberikan nama Inggris maupun nama Indonesia, dan jika
   applicable, nama natif. Ini berguna bagi aplikasi-aplikasi bilingual.

10. Hindari pemberian nama tabel ~..._list~ atau ~list_of_...~ (atau aliasnya
    ~daftar_~) karena redundan. Contoh: ~country~ dan ~negara~, tidak perlu
    ~country_list~ atau ~daftar_negara~; ~ind_district~ dan ~kecamatan_ind~,
    tidak perlu ~list_of_ind_district~ atau ~daftar_kecamatan_ind~.

    Sekali lagi, jika ragu tentang penamaan, harap hubungi editor. Konsistensi
    penamaan amat penting bagi saya.

** Dokumen ~readme.txt~

1. Gunakan format Org [5] untuk dokumen readme. Untuk memudahkan, Anda dapat
   menyunting file menggunakan Emacs (org-mode) atau vim (menggunakan salah satu
   plugin untuk TODO)

2. Gunakan marjin kanan 80 kolom. Alasan: 80 kolom adalah marjin umum yang aman
   di semua terminal.

3. Gunakan ~TODO~ untuk menandai bagian TODO (yang akan ditulis nanti).

** File ~changes.txt~

1. Gunakan format Org.

2. Satu item list untuk satu perubahan. Berikan tanggal.

3. Entri perubahan dapat ditulis dalam bahasa Indonesia atau Inggris.

** Diff commit

1. Ini sebetulnya panduan umum, tapi: usahakan untuk menghasilkan diff yang
   minimal, dengan kata lain jangan melakukan perubahan yang tidak perlu.
   Contoh: jika Anda menyunting tabel CSV di Excel, jangan mengubah urutan
   sorting, menambahkan/membuang kutip. Jangan mengubah line ending dari Unix ke
   DOS. Jika hanya satu record yang ditambahkan ke tabel, seharusnya diff hanya
   satu baris.

** Pesan commit

1. Boleh dalam bahasa Indonesia atau Inggris (saya memperkirakan semua
  kontributor rata-rata adalah orang Indonesia atau mereka yang kenal bahasa
  Indonesia).

2. Ikuti panduan pesan commit git yang baik di: TODO

** Pesan Pribadi
1. Readme ini hanya sebagian data yang saya buat. Tidak menutup kemungkinan 
   data baru akan dimasukkan guna pengembangan aplikasi ini ke depannya. 
   Diharapkan partisipasi anggota tim supaya proyek ini terlaksana dengan 
   tepat waktu. Sekian dan Selamat bekerja.

* Tautan

[0] http://Choose Ur Trashcan.org/
[1] http://id.wikipedia.org/
[2] http://www.cpan.org/
[3] http://gdi.id-php.org/
[4] mailto:kuliah.hari@gmail.com
[5] http://orgmode.org/
[6] https://github.com/
[7] https://github.com/dandy-j/Choose Ur Trashcan
[8] http://metacpan.org/module/Data::Sah
[9] http://metacpan.org/module/Perinci::Sub::Gen::AccessTable

Planning

Tahap 1
Lingkup Project : Game Mobile Berbasis Android
Kelayakan: -
Kemungkinan berhasil, dengan alasan :
- Aplikasi dapat memupuk sikap peduli lingkungan
- Aplikasi mudah digunakan(user friendly).
- Aplikasi dapat digunakan semua ponsel pintar berbasis android.

Kemungkinan tidak berhasil, dengan alasan :
- Tidak sesuai dengan kebutuhan pasar.
- Aplikasi tidak lengkap karena terdapat hambatan dalam pengerjaan.

Analisa Resiko
- Animo user (pasar) pada aplikasi ini    mungkin  tidak sesuai.

Sumber
- Internet.
- Riset keadaan lingkungan.

Tahap 2
Estimasi waktu dan usaha :
Segi waktu :
- Waktu yang melebihi batas dari waktu yang telah ditentukan (Melebihi deadline).
Segi Usaha :
- Termotivasi untuk membuat aplikasi.
- Harus ada sinkronisasi antar tiap bagian.

ANALISIS KEBUTUHAN
Software yang digunakan :
Eclipse
Cocos2D
AndEngines
   -  OpenGL ES 2.0
   -  Android 4.0.3
   -  Emulator 
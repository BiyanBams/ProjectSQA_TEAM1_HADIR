Feature: Laporan Sakit

  @positive
  Scenario: User mencari laporan sakit berdasarkan nama & tanggal yang valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User memasukkan nama di kolom search TLS1
    And User pilih tanggal mulai dan tanggal akhir TLS1
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem menampilkan data laporan sakit sesuai yang dicari

  @positive
  Scenario: User mencari laporan sakit berdasarkan nama yang valid/terdaftar
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User memasukkan nama di kolom search TLS2
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem menampilkan semua data laporan sakit berdasarkan nama yang dicari

   @positive
   Scenario: User mencari laporan sakit berdasarkan input tanggal mulai dan akhir valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User pilih tanggal mulai dan tanggal akhir TLS3
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem menampilkan semua data laporan sakit berdasarkan tanggal yang dicari

  @positive
  Scenario: User melakukan reset pada pencarian laporan sakit
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User memasukkan nama di kolom search TLS4
    And User pilih tanggal mulai dan tanggal akhir TLS4
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem menampilkan semua data laporan sakit berdasarkan tanggal yang dicari TLS4
    And User klik tombol Reset
    Then Sistem menghapus nama dan tanggal yang dicari oleh user

  @positive
  Scenario: Filter laporan sakit berdasrkan filter departemen yang ada
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User klik tombol Filter by
    And User ketik dan pilih unit "Sysmex" yang ingin dicari
    And User klik tombol Terapkan
    Then Sistem menampilkan data sesuai departemen yang dicari

  @positive
  Scenario: User dapat melihat fitur view foto
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    And User memasukkan nama di kolom search TLS6
    And Di Laporan sakit, User klik tombol Cari
    When User klik tombol view
    Then Sistem menampilkan foto dengan jelas sesuai data yang dipilih

  @positive
  Scenario: User mendownload foto yang terlampir
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    And User memasukkan nama di kolom search TLS7
    And Di Laporan sakit, User klik tombol Cari
    When User klik tombol download
    Then Sistem menampilkan foto untuk diunduh

@negative
  Scenario: User mencari laporan sakit berdasarkan nama yang invalid/tidak terdaftar
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User memasukkan nama di kolom search TLS8
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem tidak menampilkan data yang dicari atau data kosong TLS8

@negative
  Scenario: User mencari laporan koreksi berdasarkan nama valid & tanggal invalid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User memasukkan nama di kolom search TLS9
    And User pilih tanggal invalid TLS9
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem tidak menampilkan data yang dicari atau data kosong TLS9

@negative
  Scenario: User input tanggal dengan format tidak valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User pilih tanggal invalid TLS10
    And Di Laporan sakit, User klik tombol Cari
    Then Sistem tidak menampilkan data yang dicari atau data kosong TLS10

@negative
  Scenario: Filter laporan sakit berdasrkan filter departemen yang tidak ada
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    When User klik tombol Filter by departemen invalid
    And User ketik dan pilih unit "Sales" yang ingin dicari invalid
    And User klik tombol Terapkan departemen invalid
    Then Sistem tidak menampilkan data sesuai departemen yang dicari atau data kosong

@negative
  Scenario: User klik tombol view untuk foto tidak terlampir
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    And User memasukkan nama di kolom search TLS12
    And Di Laporan sakit, User klik tombol Cari
    When User klik tombol view
    Then Sistem menampilkan foto yang tidak terlampir

@negative
  Scenario: User mendownload bukti foto yang tidak terlampir
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan sakit
    And User memasukkan nama di kolom search TLS13
    And Di Laporan sakit, User klik tombol Cari
    When User klik tombol download foto tidak terlampir
    Then Sistem tidak dapat mengunduh dan menampilkan pesan error 404

  
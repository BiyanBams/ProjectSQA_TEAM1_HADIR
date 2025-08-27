Feature: Laporan Koreksi

  @positive
  Scenario: User mencari laporan koreksi berdasarkan nama & tanggal Valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User memasukkan nama di kolom search valid1
    And User pilih tanggal mulai dan tanggal akhir untuk 1 bulan penuh
    And User klik tombol Cari
    Then Sistem menampilkan data laporan koreksi sesuai yang dicari valid1

  @positive
  Scenario: User mencari laporan koreksi berdasarkan nama yang valid/terdaftar
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User memasukkan nama di kolom search valid2
    And User klik tombol Cari
    Then Sistem menampilkan semua data laporan koreksi berdasarkan nama yang dicari valid2

  @positive
  Scenario: User melakukan reset pada pencarian laporan koreksi dengan tanggal valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    And User pilih tanggal mulai dan tanggal akhir valid3
    And User klik tombol Cari
    Then Sistem menampilkan semua data laporan koreksi berdasarkan tanggal yang dicari valid3
    And User klik tombol Reset
    Then Sistem menampilkan semua data laporan koreksi berdasarkan nama yang dicari valid3

  # @positive
  # Scenario: User menyetujui pengajuan koreksi absen
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman Laporan
  #   And User masuk ke halaman Laporan Koreksi
  #   When User memasukkan nama di kolom search valid4
  #   And User pilih tanggal mulai dan tanggal akhir valid4
  #   And User klik tombol Cari
  #   Then Sistem menampilkan data laporan koreksi sesuai yang dicari valid4
  #   And User klik tombol Approval Koreksi
  #   Then Sistem menampilkan validasi pesan berhasil menyetujui koreksi absen dan field status berubah menjadi Approved

  @positive
  Scenario: User mencari laporan koreksi berdasarkan filter untuk departemen yang ada
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User klik tombol Filter by Departemen valid5
    And User pilih atau ketik unit yang ingin dicari valid5
    And User klik tombol Terapkan
    Then Sistem menampilkan data sesuai departemen yang dicari valid5

  # @positive
  # Scenario: User tidak menyetujui pengajuan koreksi absen dengan memberi alasan penolakan
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman Laporan
  #   And User masuk ke halaman Laporan Koreksi
  #   When User memasukkan nama di kolom search valid6
  #   And User pilih tanggal mulai dan tanggal akhir valid6
  #   And User klik tombol Cari
  #   Then Sistem menampilkan data laporan koreksi sesuai yang dicari valid6
  #   And User klik tombol Reject Koreksi
  #   And User mengisi alasan penolakan
  #   And User klik tombol Tolak
  #   Then Sistem menampilkan validasi pesan berhasil menolak permintaan koreksi absen dan field status berubah menjadi Rejected

  @negative
  Scenario: User mencari laporan koreksi berdasarkan sebagian nama yang invalid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User memasukkan nama yang invalid1 pada kolom search
    And User klik tombol Cari
    Then Sistem tidak menampilkan data yang dicari data kosong invalid1

  @negative
  Scenario: User mencari laporan koreksi berdasarkan nama valid & tanggal invalid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User memasukkan nama yang valid1 pada kolom search
    And User pilih tanggal invalid2
    And User klik tombol Cari
    Then Sistem tidak menampilkan data yang dicari invalid2

  @negative
  Scenario: User input tanggal dengan format tidak valid
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User pilih tanggal mulai dan akhir invalid3
    And User klik tombol Cari
    Then Sistem tidak menampilkan data yang dicari invalid3

  # @negative
  # Scenario: User tidak menyetujui pengajuan koreksi absen tanpa memberi alasan penolakan
  #   Given User login sebagai admin
  #   And User masuk ke dashboard
  #   And User masuk ke halaman Laporan
  #   And User masuk ke halaman Laporan Koreksi
  #   When User memasukkan nama di kolom search invalid4
  #   And User pilih tanggal mulai dan akhir invalid4
  #   And User klik tombol Cari
  #   And User klik tombol reject koreksi invalid4
  #   And User mengisi alasan penolakan invalid4
  #   And User klik tombol Tolak invalid4
  #   Then Sistem memberikan pop up message please fill out this field invalid4

  @negative
  Scenario: User mencari laporan koreksi berdasarkan filter untuk departemen yang tidak ada
    Given User login sebagai admin
    And User masuk ke dashboard
    And User masuk ke halaman Laporan
    And User masuk ke halaman Laporan Koreksi
    When User klik tombol filter by departemen invalid6
    And User pilih atau ketik departemen yang tidak ada
    And User klik tombol Terapkan
    Then Sistem tidak menampilkan data sesuai departemen alias kosong

Feature: Laporan Lembur

#  @Positive
#  Scenario: User mencari laporan lembur berdasarkan nama yang valid/terdaftar
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Elva" di kolom search
#    And User klik tombol Cari
#    Then Sistem menampilkan data laporan lembur sesuai yang dicari

#  @Positive
#  Scenario: User mencari laporan lembur berdasarkan nama & tanggal yang valid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Elva" di kolom search
#    And User memilih tanggal dari "10" sampai "13" di bulan "February" tahun "2023"
#    And User klik tombol Cari
#    Then Sistem menampilkan data laporan lembur sesuai yang dicari

#  @Positive
#  Scenario: User mencari laporan lembur berdasarkan input tanggal mulai dan akhir valid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memilih tanggal dari "03" sampai "04" di bulan "May" tahun "2023"
#    And User klik tombol Cari
#    Then Sistem menampilkan data laporan lembur sesuai yang dicari
#
#  @Positive
#  Scenario: User melakukan reset pada pencarian laporan lembur
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Elva" di kolom search
#    And User memilih tanggal dari "10" sampai "13" di bulan "February" tahun "2023"
#    And User klik tombol Cari
#    And User klik tombol Reset
#    Then Sistem menghapus data sebelumnya yang dicari oleh user

#  @Positive
#  Scenario: User melakukan export berdasarkan pencarian tanggal valid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memilih tanggal dari "03" sampai "04" di bulan "May" tahun "2023"
#    And User klik tombol Cari
#    And User klik button Export
#    Then Sistem berhasil mengunduh data lembur sesuai tanggal yang dicari berupa file excel
#
#  @Positive
#  Scenario: User mencari laporan lembur berdasarkan Filter untuk departemen yang ada
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User klik tombol Filter by
#    And User memilih unit "Air Asia"
#    And User klik Terapkan
#    Then Sistem menampilkan data laporan lembur sesuai yang dicari
#
#  @Positive
#  Scenario: User mengubah data atasan yang diminta
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User klik button edit pada kolom aksi
#    And User klik button ubah data
#    Then Sistem menampilkan validasi berupa message "Berhasil Mengubah Atasan"

#  @Negative
#  Scenario: User mencari laporan lembur berdasarkan sebagian nama yang invalid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Nova" di kolom search
#    And User klik tombol Cari
#    Then Sistem tidak menampilkan data yang dicari atau data kosong
#
#  @Negative
#  Scenario: User mencari berdasarkan nama valid & tanggal invalid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Noviar Budi" di kolom search
#    And User memilih tanggal dari "18" sampai "23" di bulan "August" tahun "2024"
#    And User klik tombol Cari
#    Then Sistem tidak menampilkan data yang dicari atau data kosong
#
#  @Negative
#  Scenario: User input tanggal dengan format tidak valid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memilih tanggal dari "26" sampai "29" di bulan "September" tahun "2023"
#    And User klik tombol Cari
#    Then Sistem tidak menampilkan data yang dicari atau data kosong
#
#  @Negative
#  Scenario: User melakukan export berdasarkan pencarian tanggal invalid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memilih tanggal dari "18" sampai "23" di bulan "August" tahun "2024"
#    And User klik tombol Cari
#    And User klik button Export
#    Then Sistem berhasil mengunduh namun dengan data kosong
#
#  @Negative
#  Scenario: User melakukan export berdasarkan pencarian nama valid & tanggal invalid
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Noviar Budi" di kolom search
#    And User memilih tanggal dari "18" sampai "23" di bulan "August" tahun "2024"
#    And User klik tombol Cari
#    And User klik button Export
#    Then Sistem berhasil mengunduh namun dengan data kosong
#
#  @Negative
#  Scenario: User melakukan export berdasarkan pencarian nama
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User memasukkan nama "Hadir SQA Testing 1" di kolom search
#    And User klik tombol Cari
#    And User klik button Export
#    Then Sistem menampilkan pesan "Harap isi tanggal terlebih dahulu"
#
#  @Negative
#  Scenario: User melakukan export tanpa ada inputan apapun
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User klik button Export
#    Then Sistem menampilkan pesan "Harap isi tanggal terlebih dahulu"
#
#  @Negative
#  Scenario: User mencari laporan lembur berdasarkan Filter untuk departemen yang tidak ada
#    Given User login sebagai admin
#    And User masuk ke dashboard
#    And User masuk ke halaman Laporan lembur
#    When User klik tombol Filter by
#    And User memilih unit "deletedummy"
#    And User klik Terapkan
#    Then Sistem tidak menampilkan data yang dicari atau data kosong
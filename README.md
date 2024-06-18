# Sistem Informasi Pelaporan Kerusakan (SI-PELAKU)

## Deskripsi
SI-PELAKU adalah aplikasi berbasis Java yang memungkinkan siapapun untuk melaporkan kerusakan barang. Atas petunjuk dosen aplikasi ini menggunakan database SQLite untuk menyimpan data pengguna dan pengaduan kerusakan.

## Fitur
- Registrasi Admin: Seseorang pegawai dapat mendaftar dengan nama pengguna, NIP, dan kata sandi.
- Login: Admin dapat login dengan nama pengguna dan kata sandi.
- Pelaporan Kerusakan: Siapapun dapat mengirim laporan pelaporan kerusakan barang.
- Profil Akun: Admin dapat melihat dan memperbarui password akun mereka.
- Dashboard: Tampilan utama setelah login, menampilkan fitur Data Pengaduan (daftar semua laporan yang masuk) dan Profil Akun (melihat informasi akun).

## Prasyarat
- Java Development Kit (JDK) versi 8 atau lebih baru
- Maven (jika menggunakan Maven untuk manajemen dependensi)
- SQLite JDBC Driver (sudah disertakan dalam proyek)

## Penggunaan
### Umum
- Home: Untuk mengecek laporan yang pernah dikirimkan berdasarkan nomor laporan.
- Form Pengaduan: Untuk melaporkan kerusakan yang terjadi.
### Admin
1. Registrasi: Buka aplikasi dan pilih opsi untuk mendaftar. Isi formulir registrasi dengan nama pengguna, NIP, dan kata sandi.
2. Login: Masukkan nama pengguna dan kata sandi Anda, kemudian klik tombol login.
3. Dashboard: Gunakan dashboard untuk mengakses fitur-fitur aplikasi lainnya.
   - Data Pengaduan: Melihat semua laporan yang masuk dan mengubah status laporan serta memberikan catatan mengenai laporan tersebut.
   - Profil Akun: Melihat informasi akun yang sedang login dan mengubah password.

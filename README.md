# Sistem Pengaduan Kerusakan Barang

## Deskripsi
Sistem Pengaduan Kerusakan Barang adalah aplikasi berbasis Java yang memungkinkan pengguna untuk melaporkan kerusakan barang. Aplikasi ini menggunakan database SQLite untuk menyimpan data pengguna dan pengaduan kerusakan.

## Fitur
- Registrasi Pengguna: Pengguna dapat mendaftar dengan nama pengguna, NIP, dan kata sandi.
- Login: Pengguna dapat login dengan nama pengguna dan kata sandi.
- Pengaduan Kerusakan: Pengguna dapat mengirim laporan pengaduan kerusakan barang.
- Profil Akun: Pengguna dapat melihat dan memperbarui informasi akun mereka.
- Dashboard: Tampilan utama setelah login, menampilkan berbagai fitur aplikasi.

## Prasyarat
- Java Development Kit (JDK) versi 8 atau lebih baru
- Maven (jika menggunakan Maven untuk manajemen dependensi)
- SQLite JDBC Driver (sudah disertakan dalam proyek)

## Penggunaan
### Umum
- Home: Untuk mengecek pengaduan yang pernah dikirimkan.
- Form Pengaduan: Untuk melaporkan/mengirimkan pengaduan.
### Admin
1. Registrasi: Buka aplikasi dan pilih opsi untuk mendaftar. Isi formulir registrasi dengan nama pengguna, NIP, dan kata sandi.
2. Login: Masukkan nama pengguna dan kata sandi Anda, kemudian klik tombol login.
3. Dashboard: Gunakan dashboard untuk mengakses fitur-fitur aplikasi lainnya.
   - Data Pengaduan: Melihat semua laporan yang masuk dan mengubah status laporan serta memberikan catatan mengenai laporan tersebut
   - Profil Akun:

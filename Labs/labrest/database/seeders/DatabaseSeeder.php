<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;


class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        $this->call(FacturaSeeder::class);
        User::factory(1)->create([
            'name' => 'Test User',
            'email' => 'ricardo@gmail.com',
            'role' => 0,
            'password' => '12345678',
        ]);
        //expositor de factory
        $this->call(ExpositorSeeder::class);
        //cliente de factory
        $this->call(ClienteSeeder::class);

        $this->call(TituloSeeder::class);

        
    }
}

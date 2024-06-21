<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use App\Models\Temp;

class TempSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Temp::factory()->count(10)->create();
    }
}

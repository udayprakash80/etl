/*
  Warnings:

  - The primary key for the `post` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - The `id` column on the `post` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - The primary key for the `userinfo` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - The `id` column on the `userinfo` table would be dropped and recreated. This will lead to data loss if there is data in the column.
  - Changed the type of `authorid` on the `post` table. No cast exists, the column would be dropped and recreated, which cannot be done if there is data, since the column is required.

*/
-- DropForeignKey
ALTER TABLE "post" DROP CONSTRAINT "post_authorid_fkey";

-- AlterTable
ALTER TABLE "post" DROP CONSTRAINT "post_pkey",
DROP COLUMN "id",
ADD COLUMN     "id" SERIAL NOT NULL,
DROP COLUMN "authorid",
ADD COLUMN     "authorid" INTEGER NOT NULL,
ADD CONSTRAINT "post_pkey" PRIMARY KEY ("id");

-- AlterTable
ALTER TABLE "userinfo" DROP CONSTRAINT "userinfo_pkey",
DROP COLUMN "id",
ADD COLUMN     "id" SERIAL NOT NULL,
ADD CONSTRAINT "userinfo_pkey" PRIMARY KEY ("id");

-- AddForeignKey
ALTER TABLE "post" ADD CONSTRAINT "post_authorid_fkey" FOREIGN KEY ("authorid") REFERENCES "userinfo"("id") ON DELETE RESTRICT ON UPDATE CASCADE;

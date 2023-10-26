package com.example.swimminginstructorlocator

import androidx.fragment.app.Fragment
import com.example.swimminginstructorlocator.adapter.MainPagerAdapter
import com.example.swimminginstructorlocator.databinding.ActivityMainBinding
import com.example.swimminginstructorlocator.ui.teacher.detail.TeacherDetailFragment
import com.example.swimminginstructorlocator.ui.teacher.search.SearchTeacherFragment
import com.example.swimminginstructorlocator.utils.base.BaseViewBindingActivity

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private val pagerAdapter: MainPagerAdapter by lazy {
        MainPagerAdapter(this, getFragmentList())
    }

    override fun createBindingActivity(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> binding.viewPager.currentItem = 0
                R.id.navigation_dashboard -> binding.viewPager.currentItem = 1
                R.id.navigation_notifications -> binding.viewPager.currentItem = 2
                R.id.action_teacher_detail -> binding.viewPager.currentItem = 3
                R.id.action_search_teacher -> binding.viewPager.currentItem = 4
            }

            return@setOnItemSelectedListener true
        }
    }

    override fun initData() {
    }



//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        val navView: BottomNavigationView = binding.navView
////
////        val navController = findNavController(R.id.nav_host_fragment_activity_main)
////        // Passing each menu ID as a set of Ids because each
////        // menu should be considered as top level destinations.
////        val appBarConfiguration = AppBarConfiguration(setOf(
////                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
////        setupActionBarWithNavController(navController, appBarConfiguration)
////        navView.setupWithNavController(navController)
//
//
//    }

    private fun getFragmentList(): List<Fragment> {
        return listOf(
            HomeFragment.newInstance("Param 1", "Param 2"),
            HomeFragment.newInstance("Param 1", "Param 2"),
            HomeFragment.newInstance("Param 1", "Param 2"),
            SearchTeacherFragment.newInstance("Param 1", "Param 2"),
            TeacherDetailFragment.newInstance("Param 1", "Param 2"),
        )
    }
}